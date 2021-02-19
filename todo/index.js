const dotenv = require('dotenv');
const { router } = require('./routes/routes');
dotenv.config();
const express = require('express');
const port = process.env.PORT || 3001;
const livereload = require('livereload');
const liveReloadServer = livereload.createServer();

const connectLiveReload = require('connect-livereload');

const app = express();
const urlencodedParser = express.urlencoded({ extended: true });
const jsonParser = express.json();
const staticAssetsPath = './staticResources';
liveReloadServer.watch(staticAssetsPath)
const staticAssetsMiddleware = express.static(staticAssetsPath);

app.use(connectLiveReload());
app.use('/', staticAssetsMiddleware);
app.use(urlencodedParser);
app.use(jsonParser);
app.use('/api/todos', router);

liveReloadServer.server.once("connection", () => {
    setTimeout(() => {
      liveReloadServer.refresh("/");
    }, 100);
  });

app.listen(port, () => {
    console.log(`express is listening on port: ${ port }!`);
    console.log('blah');
});
