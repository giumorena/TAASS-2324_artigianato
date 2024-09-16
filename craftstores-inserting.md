# Inserting Craftsores

To **add a new craftstore** via **backend API** (using a tool for API like Postman), you have to

1. **Add the craftsman** who owns the craftstore (maybe more than one) by sending at the URL http://localhost:8080/craftsman/addCraftsman an HTTP **POST** with for example the body
```shell
{
    "name":"John Smith",
    "email":"john.smith@gmail.com",
    "craftstoreList":
    [
        {
            "craftstoreId":1,
            "craftstoreName":"I giochi dei sogni"
        }
    ]
}
```
2. **Add the craftstore sampler** with its products by sending at the URL http://localhost:8080/sampler/addSampler an HTTP **POST** with for example the body
```shell
{
    "craftstoreId":1,
    "productList":
    [
        {
            "description": "Gufo di legno",
            "price": 25
        },
        {
            "description": "Ballerini sul filo",
            "price": 30
        }
    ]
}
```
3. **Add the craftstore** with its data by sending at the URL http://localhost:8080/craftstore/addCraftstore an HTTP **POST** with for example the body
```shell
{
    "name":"I giochi dei sogni",
    "category":"giocattoli",
    "description": "Giocattoli in legno",
    "samplerId":1,
    "ownerList":[
        {
            "craftsmanId":1,
            "craftsmanName": "John Smith"
        }
    ],
    "contactList":[
        {
            "type":"cellulare",
            "contactDetail":"33568554",
            "description":null
        },
        {
            "type":"email",
            "contactDetail":"john.smith@gmail.com",
            "description":null
        }

    ],
    "addressList":[
        {
            "region":"Toscana",
            "province":"Firenze",
            "city":"Firenze",
            "street":"via dei campi 1",
            "cap":50100
        }
    ],
    "commentList":[]
}
```

> **NOTE**: In entering the data, **some data must match**.
> - The _craftstoreId_ inserted in the craftsman and in the sampler must match the one that will be given to the craftstore that is auto increment
> - The _craftstoreName_ inserted in the craftsman must match the one entered in the craftstore
> - The _samplerId_ inserted in the craftstore must match the one that will be given to the sampler that is auto increment
> - The _email_ inserted in the craftsman should be the same one it will provide **when it registers to log in**
