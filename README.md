
### Official Documentation : Delivery Guy :15 April 2024

[![gitHub license](https://img.shields.io/badge/license-BSD3-blue.svg)](./LICENSE)
[![OpenSSF Scorecard](https://api.securityscorecards.dev/projects/github.com/atsign-foundation/at_java/badge)](https://securityscorecards.dev/viewer/?uri=github.com/atsign-foundation/at_java&sort_by=check-score&sort_direction=desc)
[![OpenSSF Best Practices](https://www.bestpractices.dev/projects/8116/badge)](https://www.bestpractices.dev/projects/8116)



## motivation 
One day, while I was reading reviews on TakeApp, I noticed that sellers were complaining about the lack of a delivery management system.
This resonated with me because of the current situation in Zimbabwe, where small shop owners struggle to deliver their products efficiently to customers.
Another challenge is that buyers often need internet access, which can be limited in Zimbabwe. 

This inspired me to create software that allows people to track their orders directly on WhatsApp.
That's how Delivery Guy was born. However, before development began, I needed to find a client in the delivery business. We partnered with a client who believed in my vision, and that partnership is what helped Delivery Guy get to where it is today.


🛠️How it works
</br>

### Demo
You can access the web demo at https://tsc-admin.vercel.app/
```
The credential
email: demo@deliveryguy.com
password: demo
```


***
Shop owners
</br>
1. create you account at https://tsc-admin.vercel.app/
2. After that create your Deliveries
    </br>
   Add clients number so that they get immediate notications as thier order get created

   ![]("https://photos.app.goo.gl/yYvH4Yu6ZEFeSZQc7" "Client Receiving Delivery Update")
3. Continiously Update the state of the order and the client will receive appropriate updates



***
Clients (Delivery  Owner)
</br>
- All you need is to say Hi to `+263 785 395 827`.
- Your will be presented with all the pending deliveries linked to your number
-   ![]("https://photos.app.goo.gl/yYvH4Yu6ZEFeSZQc7" "client getting list of deliveries linked to their account " )

- A client can optionally manually enter their delivery tracking number and they get the status of their delivery.

Client Receiving a newly created order updates

![Client Delivery](https://learncodeonline.in/mascot.png "Cli" )



****
### Features Supported : 27 May 2024 (prototype)
Customers

- [x] create client account details
- [x] Update client Details 
- [x] order all order with latest first



*** client requested Features ***
- [ ] import existing clients ( csv)


*** Deliveries
- [x] create a delivery.
- [x]  client to receive a delivery   via whatsapp.
- [x] bulk  delivery update.
- [x] Dynamic delivery creation .



*** requested  features 
- [ ] order steps should be dynamic depending on the client ( should  have default steps).
- [ ] attach proof of payment to order ( image and pdf docs).
- [ ] Link to payment gateways(v2 ).
- [ ] Generate and send invoice to clients ( v2 ).



***
Tools used In This Project 

Backend ( Core Application )
-  Java 17
- Spring Bot
- Maven
- MySQL

Backend ( WhatsApp Chat Bot  ) 
- Nodejs
- javascript
- WhatsApp Official Documentation

Frontend ( Shop Owner )
<br/>
In Delivery  guy context the Shop owner is the one who creates deliveries and give tracking number to client .
- NextJs
- JavaScript 
- NextAuth ( For Authentication and Session Management)


***
Deployment 

- Vercel
- docker 
- ec2
- GitHub actions 

***
## System Design 
One Thing i have learnt from software engineering is that systems always change as long as it serves people of dynamic needs 
In my case i started with orders the system was more of order management by then it was called Delivery Express , but it 
Evolved to Delivery Guy Below is a basic Schematic Diagram we used to boot Strap the building of the system .

![](https://github.com/dzzirima/tih_backend/blob/main/src/main/resources/images/system_design.png " Schematic Diagram " )

## User User Interface

### main dashboard
![ ](https://github.com/dzzirima/tih_backend/blob/main/src/main/resources/images/main_dash_board.png  "Main_DashBoard" )

### Share Delivery on WhatsApp and Delivery Timelines 
![ ](https://github.com/dzzirima/tih_backend/blob/main/src/main/resources/images/time_lines.png  "Time_Lines" )


















