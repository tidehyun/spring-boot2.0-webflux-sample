<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/demo.css" rel="stylesheet">
    
    <script src="/js/jquery.min.js"></script>
  </head>

  <body class="text-center">
    <form class="form-signin">
      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="email" id="email" name="email" class="form-control" placeholder="Email address" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="text" id="name" name="name" class="form-control" placeholder="Name" required>
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="button" id="sign">Sign in</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
  </body>
  
  <script>
	$(document).ready(
			function() {

				$('#sign').click(
						function() {
							var person = new Object();
							person.email = $('#email').val();
							person.name = $('#name').val();
							
							$.ajax({
							  url:'/functional/person',
							  type:'POST',
							  async:true,
							  data: JSON.stringify(person),
							  contentType:'application/json; charset=utf-8',
							  success:function(data){
							  	alert(data);
							  }
							});
						});

			});
</script>
  
</html>
