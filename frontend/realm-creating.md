# Creating a realm

A **realm** is a space where you manage objects, including users, applications, roles, and groups. A user belongs to and logs into a realm.
For more details see the [Keycloak Server Administration Guide](https://www.keycloak.org/docs/latest/server_admin/#configuring-realms)

To **create a realm** you need to perform the following procedure

1.Go to the **Keycloak Administration Console** using the URL [http://localhost:8090/admin/master/console](http://localhost:8090/admin/master/console)

2.Enter **username** and **password** both equal to _admin_

3.On the top left click Keycloak next to master realm, then click **Create Realm**

4.In the Resource file section, click **Browser** and then select the [realm-export.json](../realm-export.json) file in root folder

5.The **realm name** will be _craftrealm_

6.Click **Create**