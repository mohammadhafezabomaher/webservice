const { Eureka } = require("eureka-js-client");

// Configure Eureka client for Node.js
const eurekaClient = new Eureka({
  instance: {
    app: "node-service", // Unique name of your Node.js service
    instanceId: `node-service-${process.env.PORT || 3000}`, // Unique instance ID
    hostName: "localhost",
    ipAddr: "127.0.0.1",
    port: {
      $: process.env.PORT || 3000,
      "@enabled": true,
    },
    vipAddress: "node-service",
    dataCenterInfo: {
      "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
      name: "MyOwn",
    },
  },
  eureka: {
    host: "localhost", // Host where your Eureka server is running
    port: 8761, // Port where your Eureka server is running
    servicePath: "/eureka/apps/",
  },
});

eurekaClient.start((error) => {
  if (error) {
    console.log("Error connecting to Eureka:", error);
  } else {
    console.log("Node service registered with Eureka");
  }
});

module.exports = eurekaClient;
