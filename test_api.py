import requests

url = "http://127.0.0.1:5000/api/analyze"

data = {
    "sample_name": "Maize Silage A",
    "sample_type": "Silage",
    "moisture": 18.2,
    "ph": 5.8,
    "temperature": 32,
    "humidity": 81
}

response = requests.post(url, json=data)

print("Status Code:", response.status_code)
print("Response:")
print(response.json())