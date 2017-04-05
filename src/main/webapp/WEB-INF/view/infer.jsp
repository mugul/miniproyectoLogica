<%-- 
    Document   : insertarEvaluar
    Created on : 24/05/2014, 08:32:15 PM
    Author     : federico
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>

    <!-- Desde aqui -->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/desplegar.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mathjax-MathJax-v2.3-248-g60e0a8c/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
        <script type="text/javascript">
            function limpiar()
            {
                var texArea=document.getElementById('termino_string');
                if(texArea.value != "")
                {
                    if(confirm("Seguro que desea borrar el contenido del área de texto"))
                        texArea.value="";
                }
            }
      

        </script>
        <base href="/Miniproyecto/perfil/${usuario.login}/"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" >
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" >
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-responsive.css" >
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <tiles:insertDefinition name="style" />
        <title>David | Demostrar</title>
    </head>
    <body>
        <tiles:insertDefinition name="header" />

        <script>
            function insertAtCursor(myField, myValue) 
            {            
                myValue+="";
                parent.window.document.getElementById(myField).value = myValue;
            }
        </script>

        <div style="float: right; width: 600px;">
            <article id="teoremas">
                <h3 style="margin: 0px;padding:0px;height:40px;"><a onclick="desplegar('teoremas')">Teoremas</a></h3>
                <ul>
                    <c:forEach items="${categorias}" var="cat"> 
                        <li style="list-style: none; color: #03A9F4"><h4>${cat.getNombre()}</h4>
                            <ul>
                                <c:forEach items="${teoremas}" var="teo">
                                    <c:choose>
                                        <c:when test="${teo.getCategoria().getId()==cat.getId()}">      
                                            <c:choose>
                                                <c:when test="${resuelveManager.getResuelveByUserAndTeorema(usuario.login,teo.getId()).isResuelto() == false}">
                                                    <li style="list-style: none;">
                                                        <h6 style="color: #000;">
                                                            <i class="fa fa-lock" aria-hidden="true" style="margin-right: 10px;"></i>
                                                            <a onclick="insertAtCursor('nStatement_id', '${teo.getId()}')">
                                                                ${resuelveManager.getResuelveByUserAndTeorema(usuario.login,teo.getId()).getNumeroteorema()}.- ${teo.getTeoIzqTerm().toStringInFin()} == ${teo.getTeoDerTerm().toStringInFin()}
                                                            </a>
                                                            <a onclick="expandMeta(${teo.getId().toString()})"><i class="fa fa-plus-circle" aria-hidden="true"  style="margin-left: 10px; margin-right: 10px;"></i></a>
                                                            <span style="display: none;" id="${teo.getId()}">
                                                                ${metateoremas.getMetateorema(teo.getId()).getEnunciadoizq()} == ${metateoremas.getMetateorema(teo.getId()).getEnunciadoder()}
                                                            </span>
                                                        </h6>
                                                    </li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li style="list-style: none;">
                                                        <h6 style="color: #000;">
                                                            <i class="fa fa-unlock" aria-hidden="true" style="margin-right: 10px;"></i>
                                                            <a onclick="insertAtCursor('nStatement_id', '${teo.getId()}')">
                                                                ${resuelveManager.getResuelveByUserAndTeorema(usuario.login,teo.getId()).getNumeroteorema()}.- ${teo.getTeoIzqTerm().toStringInFin()} == ${teo.getTeoDerTerm().toStringInFin()}
                                                            </a>
                                                            <a onclick="expandMeta(${teo.getId().toString()})"><i class="fa fa-plus-circle" aria-hidden="true"  style="margin-left: 10px; margin-right: 10px;"></i></a>
                                                            <span style="display: none;" id="${teo.getId()}">
                                                                ${metateoremas.getMetateorema(teo.getId()).getEnunciadoizq()} == ${metateoremas.getMetateorema(teo.getId()).getEnunciadoder()}
                                                            </span>
                                                        </h6>
                                                    </li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach> 
                </ul>
            </article>     
        </div>

        <script>
            function expandMeta(id) {
                elem = document.getElementById(id);
                if (elem.style.display == "inline")
                    elem.style.display = "none";
                else
                    elem.style.display = "inline";

            };
        
            function getMetateo(id) {
            
            }
        </script>

        <script>
            t=document.getElementById('pasoAnt');
            t.innerText="${pasoAnt}";
        </script>

        <div style="width: 500px; height: 100px;">
            <h5>${formula}</h5>
        </div>    

        <sf:form action="/Miniproyecto/infer/${usuario.getLogin()}" method="POST" modelAttribute="infer">
            Paso anterior:<br><sf:input path="pasoAnt" id="pasoAnt_id" value="${pasoAnt}"/><sf:errors path="pasoAnt" cssClass="error" />
            <br>
            <sf:input type="hidden" name="solucionId" path="solucionId" id="solucionId" value="${solucionId}"/>
            <!--\cssId{eq}{\style{cursor:pointer;}{p\equiv q}}-->
            Teorema a usar:<br>
            <sf:input path="nStatement" id="nStatement_id" value="${nStatement}"/><sf:errors path="nStatement" cssClass="error" />
            <%--<select style="width: auto; height: auto; border: none;" class="form-control" id="mensaje" name="nStatement">
                <c:forEach items="${teoremas}" var="cat">
                    <option value="${cat.getId()}" >${cat.getCategoria().getNombre()} - ${cat.getEnunciadoizq()} == ${cat.getEnunciadoder()}</option>
                </c:forEach>  
            </select>--%>
            <br>
            Instanciación:<br><sf:input path="instanciacion" id="instanciacion_id" value="${instanciacion}"/><sf:errors path="instanciacion" cssClass="error" /></br>
            Leibniz:<br><sf:input path="leibniz" id="leibniz_id" value="${leibniz}"/><sf:errors path="leibniz" cssClass="error" /></br>
            <input class="btn" type="submit" value="Inferir"> <input class="btn" type="button" value="limpiar" onclick="limpiar()">

        </sf:form>
        <%-- <a href="/Miniproyecto/perfil/${usuario.getLogin()}">Perfil</a>--%>
        <br>

        <tiles:insertDefinition name="footer" />
    </body>
</html>
