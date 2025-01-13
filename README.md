# inventoryrepo
Inventory Service maintaining the Item details and its inventory details. <br/>
Provides REST API for the client application to update the Item inventory.<br/>
If item inventory goes down for cerntain threshold then makes order to Order service over Kafa topic. <br/>
<br/>
https://github.com/shaileshkulkarni/inventoryrepo/blob/main/Garage%20B%20Solution.pdf provides details of the overall architecture and design.
<br />

Related Repositories
Order Service Repository - https://github.com/shaileshkulkarni/OrderService <br/>
Local Supplier (SupplierA) Service Repository - https://github.com/shaileshkulkarni/SupplierAService <br/>
International Supplier (SupplierB) Service Repository - https://github.com/shaileshkulkarni/SupplierBService <br/>

