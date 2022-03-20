A Sample application for running dropwizard with google inject on gradle build system.

Idea configuration for running this application:

*program Arguments:* server core/config/developement.yaml

In multiple places there is hardcoding which could be avoided

Postman Collection:

```
{
	"info": {
		"_postman_id": "cad179f9-7528-4efc-a910-98c27dcfae77",
		"name": "restbus",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "createQueue",
			"request": {
				"method": "PUT",
				"header": [],
				"url": {
					"raw": "http://localhost:15006/restbus/register_queue?name=sampleQueue",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "15006",
					"path": [
						"restbus",
						"register_queue"
					],
					"query": [
						{
							"key": "name",
							"value": "sampleQueue"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "createSubscription",
			"request": {
				"method": "PUT",
				"header": [],
				"url": {
					"raw": "http://localhost:15006/restbus/register_sub?queue_name=sampleQueue&sub_name=sampleSub&dest_http=http://localhost:15006/restbus/sink",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "15006",
					"path": [
						"restbus",
						"register_sub"
					],
					"query": [
						{
							"key": "queue_name",
							"value": "sampleQueue"
						},
						{
							"key": "sub_name",
							"value": "sampleSub"
						},
						{
							"key": "dest_http",
							"value": "http://localhost:15006/restbus/sink"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "createMessage",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\"a\":\"b\"}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:15006/restbus/produce?queue_name=sampleQueue",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "15006",
					"path": [
						"restbus",
						"produce"
					],
					"query": [
						{
							"key": "queue_name",
							"value": "sampleQueue"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "startConsumer",
			"request": {
				"method": "PUT",
				"header": [],
				"url": {
					"raw": "http://localhost:15006/restbus/start_consumer",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "15006",
					"path": [
						"restbus",
						"start_consumer"
					]
				}
			},
			"response": []
		}
	]
}
```