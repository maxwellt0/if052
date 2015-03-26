<%--
  Created by IntelliJ IDEA.
  User: valentyn
  Date: 2/9/15
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">
            <div class="container">
                <p></p>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Місто</th>
                        <th>Вулиця</th>
                        <th>Будинок</th>
                        <th>Квартира</th>
                        <th>Лічильники</th>
                        <th>Редагування</th>
                        <th>Видалення</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="address" items="${addresses}">
                        <tr>
                            <td><c:out value="${address.city}"/>  </td>
                            <td><c:out value="${address.street}"/></td>
                            <td><c:out value="${address.building}"/></td>
                            <td><c:out value="${address.apartment}"/></td>
                            <td><a href="<c:url value="/watermeter?addressId=${address.addressId}"/>">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span>
                                </button></a></td>
                            <td><a href="<c:url value="/updateAddress?addressId=${address.addressId}"/>">
                                <button class="btn btn-default"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></a></td>
                            <td><a href="<c:url value="/deleteAddress?addressId=${address.addressId}"/>">
                                <button class="btn btn-default"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button></a></td>
        
                        </tr>
                    </c:forEach>
                </table>
                    <%--hard-code url!!!--%>
                <a href="/map?userId=1">
                    <div class="btn-group btn-group-justified" role="group" aria-label="...">
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-default" >
                                <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
                                Подивитись на карті
                            </button>
                        </div>
                    </div>
                </a>

                <c:url var="addUrl" value="/addAddress"/>
                <form:form action="${addUrl}" method="post" modelAttribute="address">
                    <table class="box-table-a">
                        <caption> Додати адресу </caption>
                        <thead>
                        <tr>
                            <th>Місто</th>
                            <th>Вулиця</th>
                            <th>Будинок</th>
                            <th>Квартира</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <input class="form-control" type="text" name="city" />
                            </td>
                            <td> 
                                <input class="form-control" type="text" name="street" />
                            </td>
                            <td>
                                <input class="form-control" type="text" name="building" />
                            </td>
                            <td>
                                <input class="form-control" type="text" name="apartment" />
                            </td>
                            <td>
                                <button class="btn btn-default" type="submit">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form:form>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
