 <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
 
   <%@ page isELIgnored="false" %>
  <html>   
  <head>   
<meta http-equiv="Content-Type" content="text/html;  charset=ISO-8859-1">  
  <link rel="stylesheet"   href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"> 
    <title>Products</title> 
   </head> 
    <body> 
  <section>  
<div class="jumbotron">
 <div class="container"> 
 <h1>Customers</h1> 
  <p>All Of our Customers</p> 
 </div> 
    </div>  </section> 
 <section class="container">
  <div class="row">   
 <c:forEach items="${customers}" var="customer">  
 <div class="col-sm-6 col-md-3"> 
 <div class="thumbnail">   
  <div class="caption">  
 <h3>${customer.cusstomerId}</h3>   
   <p>${customer.customer_name}</p>  
  <p>${customer.address}</p>  
 <p>${customer.no_of_orders_made}</p> 
 </div>       
  </div> 
 </div>
 </c:forEach>  
  </div>  
  </section>
  </body> 
  </html>