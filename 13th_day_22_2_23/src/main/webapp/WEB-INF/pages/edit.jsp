<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <center>
 <h2>Edit Form</h2>
 <form:form action ="/13th_day_22_2_23/update" method="post">
 <form:hidden path="id" /><br>
 <form:input path="name" /><br>
 <form:input path="email" /><br>
 <input type="submit" value="Update">
 </form:form>
  </center>