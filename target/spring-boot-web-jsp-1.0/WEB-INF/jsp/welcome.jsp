<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
	<title>Trendyol Demo</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />

</head>

<body>
<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Trendyolt</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Configurations</a></li>
			</ul>
		</div>
	</div>
</nav>

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>All Configs</h1>

	<table class="table table-striped">
		<thead>
		<tr>
			<th>#ID</th>
			<th>Name</th>
			<th>Type</th>
			<th>Value</th>
			<th>IsActive</th>
			<th>ApplicationName</th>
		</tr>
		</thead>

		<c:forEach var="config" items="${configList}">
			<tr>
				<td>
						${config.id}
				</td>
				<td>${config.name}</td>
				<td>${config.type}</td>
				<td>${config.value}</td>
				<td>${config.active}</td>
				<td>${config.applicationName}</td>

				<td>
					<spring:url value="/configParameters/${config.id}" var="getUrl" />
					<spring:url value="/configParameters/${config.id}/delete" var="deleteUrl" />
					<spring:url value="/configParameters/${config.id}/update" var="updateUrl" />

					<button class="btn btn-info"
							onclick="location.href='${getUrl}'">Query</button>
					<button class="btn btn-primary"
							onclick="location.href='${updateUrl}'">Update</button>
					<button class="btn btn-danger"
							onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
				</td>
			</tr>
		</c:forEach>
	</table>

	<hr/>

	<c:if test="${!empty configList}">
	<form class="form-horizontal" id="addParameterForm" action="/configParameters">
		<div class="form-group">
			<label class="control-label col-sm-2" for="name">Name:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="name" name="name" placeholder="Parameter key">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="value">Value:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="value" name="value" placeholder="Parameter value">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="applicationName">Application Name:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="applicationName" name="applicationName" placeholder="Application Name">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label><input type="checkbox" id="active" name="active"> Active</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default" id="saveButton">Save</button>
			</div>
		</div>

		<input type="hidden" name="applicationName" value="SERVICE-A">
	</form>
	</c:if>

</div>

<!-- /.container -->

<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {

        $("#saveButton").submit(function( event ) {            debugger;
            // Stop form from submitting normally
            event.preventDefault();

            // Get some values from elements on the page:
            var data = {
                name : $("#name").val(),
				value : $("#value").val(),
				active : $("#active").val(),
				applicationName : $("#applicationName").val()
			}
            url = $(this).attr( "action" );

            console.log(data);
            console.log(url);
            debugger;

            $.ajax({
                method : "POST",
                contentType: "application/json",
                url: window.location + "configParameters",
                data: data,
                dataType: "json",
                cache: false,
                processData: false,
                timeout: 600000,
                success: function (data) {
                   console.log(response);

                },
                error: function (response) {
                    console.log(response);

                }
            });

        })
    });

</script>
</body>
</html>