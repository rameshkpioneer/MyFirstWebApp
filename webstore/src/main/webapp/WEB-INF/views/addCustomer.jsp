<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=ISO-8859-1">
<link rel="stylesheet"
href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>
<!-- <section>
<div class="jumbotron">
<div class="container">
<h1>Products</h1>
<p>Add products</p>
</div>
</div>
</section> -->
<section class="container">
<form:form method="POST" modelAttribute="newCustomer" class="form-horizontal">
<fieldset>
<legend>Add new Customer</legend>

<div class="form-group">
<label class="control-label col-lg-2 col-lg-2" for="productId">Customer  Id</label>
<div class="col-lg-10">
<form:input id="cusstomerId" path="cusstomerId" type="text" class="form:input-large"/>
</div>
</div>

<!--  customer_name -->

<div class="form-group">
<label class="control-label col-lg-2" for="category">Name</label>
<div class="col-lg-10">
<form:input id="customer_name" path="customer_name" rows = "2"/>
</div>
</div>



<!--  Address -->

<div class="form-group">
<label class="control-label col-lg-2" for="Address">Address</label>
<div class="col-lg-10">
<form:textarea  id="address" path="address" rows = "2"/>
</div>
</div>



<!--  no of orders made -->

<div class="form-group">
<label class="control-label col-lg-2" for="no_of_orders_made">Orders</label>
<div class="col-lg-10">
<form:input id="no_of_orders_made" path="no_of_orders_made" rows = "2"/>
</div>
</div>








<div class="form-group">
<div class="col-lg-offset-2 col-lg-10">
<input type="submit" id="btnAdd" class="btnbtn-primary" value ="Add"/>
</div>
</div>
</fieldset>
</form:form>
</section>
</body>
</html>