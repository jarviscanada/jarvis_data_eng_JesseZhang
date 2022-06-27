--Group hosts by hardware info
SELECT
    FIRST_VALUE (cpu_number) OVER(
    PARTITION BY cpu_number
    ORDER BY
      total_mem DESC
  ) AS cpu_number,
        id AS host_id,
    total_mem
FROM
    host_info;


-- round timestamp to 5 mins interval
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$ $ BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$ $ LANGUAGE PLPGSQL;

SELECT
    t1.id,
    t1.hostname,
    round5(t2.timestamp) as timestamp,
  100 - AVG(t2.memory_free * 100 / (t1.total_mem / 1024)):: int as avg_used_mem_percentage
FROM
    host_usage t2
    INNER JOIN host_info t1 ON t2.host_id = t1.id
GROUP BY
    round5(t2.timestamp),
    t1.id
ORDER BY
    round5(t2.timestamp),
    t1.id;

--Detect host failure
SELECT
    host_id,
    round5(timestamp) as ts,
    count(*) as num_data_points
FROM
    host_usage
GROUP BY
    round5(timestamp),
    host_id
HAVING
        count(*) < 3;





--sample data
/*INSERT INTO
    host_info (
    id,
    hostname,
    cpu_number,
    cpu_architecture,
    cpu_model,
    cpu_mhz,
    L2_cache,
    total_mem,
    timestamp
)
VALUES
    (
        2,
        'name2',
        1,
        'x86',
        'intel',
        2000,
        256,
        2048,
        '2019-05-29 15:00:00.000'
    ),
    (
        3,
        'name3',
        2,
        'x86',
        'intel',
        2000,
        256,
        2048,
        '2019-05-29 15:00:00.000'
    ),
    (
        4,
        'name4',
        2,
        'x86',
        'intel',
        2000,
        256,
        4088,
        '2019-05-29 15:00:00.000'
    ),
    (
        5,
        'name5',
        1,
        'x86',
        'intel',
        2000,
        256,
        1568,
        '2019-05-29 15:00:00.000'
    ),
    (
        6,
        'name6',
        2,
        'x86',
        'intel',
        2000,
        256,
        1024,
        '2019-05-29 15:00:00.000'
    ),
    (
        7,
        'name7',
        1,
        'x86',
        'intel',
        2000,
        256,
        1024,
        '2019-05-29 15:00:00.000'
    );
INSERT INTO
    host_usage (
    "timestamp",
    host_id,
    memory_free,
    cpu_idle,
    cpu_kernel,
    disk_io,
    disk_available
)
VALUES
    ('2022-05-06 02:01:00.000', 2, 3000, 90, 4, 2, 3),
    ('2022-05-06 02:02:00.000', 2, 2000, 90, 4, 2, 3);*/