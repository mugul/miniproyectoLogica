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
    <tiles:insertDefinition name="style" />
    <title>Miniproyecto</title>
  </head>
  <body>
    <tiles:insertDefinition name="header" />

    
        
    <div style="margin-top: 20px">
<!--      <h1>
        ...aquí va la demostración...
      </h1>-->
    </div>    
    <h4>${formula}</h4>
    <sf:form action="/Miniproyecto/infer/${usuario.getLogin()}" method="POST" modelAttribute="infer">
      <%--${mensaje}<br>--%>
      Paso anterior:<br><sf:input path="pasoAnt" id="pasoAnt_id" value="${pasoAnt}"/><sf:errors path="pasoAnt" cssClass="error" />
      <br><br>

      <!--\cssId{eq}{\style{cursor:pointer;}{p\equiv q}}-->
      Teorema a usar:<br>
            <select style="width: auto; height: auto; border: none;" class="form-control" id="mensaje" name="nStatement">
            <c:forEach items="${mensaje}" var="cat">
              <option value="${cat.getId()}" >${cat.getCategoria().getNombre()} - ${cat.getEnunciadoizq()} == ${cat.getEnunciadoder()}</option>
            </c:forEach>  
            </select>
      <br>
      Instaciación:<br><sf:input path="instanciacion" id="instanciacion_id" value="${instanciacion}"/><sf:errors path="instanciacion" cssClass="error" /></br>
      Leibniz:<br><sf:input path="leibniz" id="leibniz_id" value="${leibniz}"/><sf:errors path="leibniz" cssClass="error" /></br>
      <input class="btn" type="submit" value="Inferir"> <input class="btn" type="button" value="limpiar" onclick="limpiar()">

    </sf:form>
         <%-- <a href="/Miniproyecto/perfil/${usuario.getLogin()}">Perfil</a>--%>
         <br>

              
    <script>
      t=document.getElementById('termino_string');
      t.innerText="${termino}";
    </script>
    
    
      
    <tiles:insertDefinition name="footer" />
  </body>
</html>
