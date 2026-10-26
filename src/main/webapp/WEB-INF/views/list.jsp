<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Job Application Tracker</title></head>
<body>
<h1>Job Application Tracker</h1>

<c:if test="${not empty error}">
  <p style="color:red;"><c:out value="${error}"/></p>
</c:if>

<form method="post" action="applications">
  <input type="hidden" name="action" value="add">
  Company: <input type="text" name="company">
  Role: <input type="text" name="role">
  <button type="submit">Add</button>
</form>

<p>Applied: ${applied} | Interview: ${interview} | Offer: ${offer} | Rejected: ${rejected}</p>

<table border="1" cellpadding="6">
  <tr><th>ID</th><th>Company</th><th>Role</th><th>Status</th><th>Change status</th></tr>
  <c:forEach var="app" items="${applications}">
    <tr>
      <td>${app.id}</td>
      <td><c:out value="${app.company}"/></td>
      <td><c:out value="${app.role}"/></td>
      <td>${app.status}</td>
      <td>
        <form method="post" action="applications">
          <input type="hidden" name="action" value="status">
          <input type="hidden" name="id" value="${app.id}">
          <select name="status">
            <option>Applied</option>
            <option>Interview</option>
            <option>Offer</option>
            <option>Rejected</option>
          </select>
          <button type="submit">Update</button>
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
