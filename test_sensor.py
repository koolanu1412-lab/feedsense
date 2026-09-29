import requests

url = "http://127.0.0.1:5000/api/sensors"

data = {
    "moisture": 16.5,
    "temperature": 29,
    "humidity": 70,
    "ph": 5.8
}

response = requests.post(url, json=data)

print("Status Code:", response.status_code)
print(response.json())