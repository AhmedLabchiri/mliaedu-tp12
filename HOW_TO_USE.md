# How to Use the SOAP Web Service

## ✅ Your WSDL is Working!

The service is running and **fully exploitable**. Here's proof:

### Service Information
- **WSDL URL**: http://localhost:8080/services/hello?wsdl
- **Service Endpoint**: http://localhost:8080/services/hello
- **Namespace**: http://api.cxf.acme.com/

### Available Operations
1. **SayHello** - Returns a greeting message
2. **FindPerson** - Returns a Person object by ID

---

## Testing Methods

### 1. Using cURL (Command Line)

#### Test SayHello:
```bash
curl -X POST "http://localhost:8080/services/hello" \
  -H "Content-Type: text/xml; charset=utf-8" \
  -H "SOAPAction: \"\"" \
  -d '<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://api.cxf.acme.com/">
  <soap:Body>
    <ns:SayHello>
      <name>Ahmed</name>
    </ns:SayHello>
  </soap:Body>
</soap:Envelope>'
```

**Response:**
```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <ns2:SayHelloResponse xmlns:ns2="http://api.cxf.acme.com/">
      <greeting>Bonjour, Ahmed</greeting>
    </ns2:SayHelloResponse>
  </soap:Body>
</soap:Envelope>
```

#### Test FindPerson:
```bash
curl -X POST "http://localhost:8080/services/hello" \
  -H "Content-Type: text/xml; charset=utf-8" \
  -H "SOAPAction: \"\"" \
  -d '<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://api.cxf.acme.com/">
  <soap:Body>
    <ns:FindPerson>
      <id>123</id>
    </ns:FindPerson>
  </soap:Body>
</soap:Envelope>'
```

**Response:**
```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <ns2:FindPersonResponse xmlns:ns2="http://api.cxf.acme.com/">
      <person>
        <ns2:age>36</ns2:age>
        <ns2:id>123</ns2:id>
        <ns2:name>Ada Lovelace</ns2:name>
      </person>
    </ns2:FindPersonResponse>
  </soap:Body>
</soap:Envelope>
```

---

### 2. Using Java Client (CXF Proxy)

Run the `Client.java` class that was created for you:

```java
// The client creates a proxy and calls the service
HelloService service = (HelloService) factory.create();
String greeting = service.sayHello("Ahmed");
Person person = service.findPersonById("123");
```

---

### 3. Using SoapUI

1. Create a new SOAP project
2. Enter WSDL URL: `http://localhost:8080/services/hello?wsdl`
3. SoapUI will generate sample requests for both operations
4. Click the green play button to send requests

---

### 4. Using Postman

1. Create a new POST request to: `http://localhost:8080/services/hello`
2. Set Headers:
   - `Content-Type: text/xml; charset=utf-8`
   - `SOAPAction: ""`
3. In the Body, select "raw" and paste the SOAP envelope (see examples above)
4. Click Send

---

### 5. Using Browser (WSDL only)

Simply open in your browser:
- http://localhost:8080/services/hello?wsdl

This will show you the complete WSDL definition with all operations, types, and bindings.

---

## Why It Works Now

The issue was in the `Server.java` configuration:

**Before (Broken):**
```java
factory.setServiceClass(HelloServiceImpl.class); // ❌ Wrong
```

**After (Fixed):**
```java
factory.setServiceClass(HelloService.class);      // ✅ Interface
factory.setServiceBean(new HelloServiceImpl());    // ✅ Implementation
```

CXF needs:
1. The **interface** (defines the contract/WSDL)
2. The **implementation** (handles the logic)

---

## Common Issues & Solutions

### Port Already in Use
```bash
# Kill existing process
lsof -i :8080
kill -9 <PID>
```

### Change Port
```java
String address = "http://localhost:9090/services/hello"; // Use different port
```

### View Server Logs
Check the console where Server.main() is running to see incoming requests.

---

## Next Steps

1. ✅ **Service is running and working**
2. ✅ **WSDL is accessible**
3. ✅ **Both operations tested successfully**
4. You can now:
   - Create more complex operations
   - Add security (WS-Security)
   - Deploy to a production server
   - Generate client code with wsdl2java

Enjoy your working SOAP web service! 🎉

