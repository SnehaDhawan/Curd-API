<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
 <table border="1px">
 <tr>
 <th>Id</th>
 <th>Name</th>
 <th>Email</th>
 <th>Action</th>
</tr>

<c:forEach items="${l1}" var="e">
 <tr>
 <td>${e.id}</td>
 <td>${e.name}</td>
 <td>${e.email}</td>
 <td>
 <a href="del/${e.id}" >Delete</a>
 <a href="edit/${e.id}" >Edit</a>
 </td>
 </tr>
 </c:forEach>
 </table>