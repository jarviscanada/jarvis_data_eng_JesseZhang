
# Introduction
Based on the Trading Platform backend project, this frontend application renders the information to users and receives users' input. Users, usually the administrators using the trading system, can see a list of all traders, a daily list of quotes and details of each trader. Also, they can create or delete a trader, deposit funds into a trader's account and withdrawal funds from the account. Basic input field validations are implemented in this project to display warning messages to users. Running two backend docker containers(one is PostgreSQL and one is SpringBoot application), this project can be developed and tested using React Javascripte language. It uses npm (installed with Node.js) to manage dependencies. Manual integration tests are done through web browsers and the application is dockerized at last.

# Quick Start
Use markdown code block for your quick-start commands
- Have Docker setup
    ```
    #Docker version must be 17.05 or higher
    docker -v

    #download backend app images
    docker pull natsumeqi/trading-app
    docker pull natsumeqi/trading-psl
    docker pull natsumeqi/trading-front-react

    #create a new docker network
    docker network create --driver bridge trading-net
    ```
- Have up & running docker image of backend application (provide docker image)
    ```
    #start two backend docker containers
    docker run --name trading-psql-dev  -e POSTGRES_PASSWORD=password  -e POSTGRES_DB=jrvstrading  -e POSTGRES_USER=postgres  --network trading-net  -d -p 5432:5432 trading-psl

    export IEX_PUB_TOKEN={your IEX public token}
    docker run --name trading-app-dev  -e "PSQL_URL=jdbc:postgresql://trading-psql-dev:5432/jrvstrading"  -e "PSQL_USER=postgres"  -e "PSQL_PASSWORD=password"  -e "IEX_PUB_TOKEN=${IEX_PUB_TOKEN}"  --network trading-net  -p 8080:8080 -t trading-app 

    #start the frontend docker container
    docker run -p 3000:3000 -d trading-front-react
    ```
- Install node & npm

   https://docs.npmjs.com/downloading-and-installing-node-js-and-npm 
- Run npm start
   ```
    npm install
    npm start
   ``` 

# Implemenation
As the architecture diagram below, the dashboard page contains a list of current traders and a navigation bar. The quote page can be accessed by the icon on the navigation bar and displays a list of quotes in the trading system. After clicking on one trader from the trader list on the dashboard page, a Trader Account Details page will be directed and show the information about the trader, as well as the functionalities to deposit and withdraw funds.

## Architecture
![](/trading-ui/public/Frontend.png)

# Test
The test was done by manual integration tests.

# Deployment
The source code is hosted on Github. The app is dockerized.

# Improvements

- handle Quote adding/removing etc.
- show more specific message about input validations (e.g. show the balance is not sufficient)
- more user friendly UI