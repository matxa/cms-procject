# Frontend for cms-api

To test with dummy data please run
```python3
python3 mongo_dump.py 
```

Before running above script make sure cms-api is running and `prevent` duplicate Documents

Change the `API_URL` variable to match the host and port the cms-api is running on

# SETUP 👨🏾‍💻

1 - Create a virtual environmnet `preference`

```bash
python3 -m pip install virtualenv
python3 -m venv env
source env/bin/activate
```

2 - Install requirements
```bash
pip install -r requirements.txt
```

3 - Run server
```bash
python3 server.py
```