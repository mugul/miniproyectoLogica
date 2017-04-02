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
    <title>MP|Demostrar</title>
  </head>
  <body>
    <tiles:insertDefinition name="header" />
    
    <script>
            function insertAtCursor(myField, myValue) 
      {            
        myValue+="";
        //IE support
        if (parent.window.document.selection) {
          parent.window.document.getElementById(myField).focus();
          sel = parent.window.document.selection.createRange();
          sel.text = myValue;
        }
        //MOZILLA and others
        else if (parent.window.document.getElementById(myField).selectionStart || 
                 parent.window.document.getElementById(myField).selectionStart == '0') {
          var startPos = parent.window.document.getElementById(myField).selectionStart;
          var endPos = parent.window.document.getElementById(myField).selectionEnd;
          var newPos = startPos + myValue.length
          parent.window.document.getElementById(myField).value = parent.window.document.getElementById(myField).value.substring(0, startPos)
              + myValue
              + parent.window.document.getElementById(myField).value.substring(endPos, parent.window.document.getElementById(myField).value.length);
          parent.window.document.getElementById(myField).selectionStart = newPos;
          parent.window.document.getElementById(myField).selectionEnd = newPos;
        } else {
          parent.window.document.getElementById(myField).value += myValue;
          parent.window.document.getElementById(myField).selectionStart = newPos;
          parent.window.document.getElementById(myField).selectionEnd = newPos;
        }
        parent.window.document.getElementById(myField).focus();
      }
    </script>

    <div style="float: right; width: 600px;">
      <p>${categorias}</p>
      <article id="teoremas" >
        <h3 style="margin: 0px;padding:0px;height:40px;"><a onclick="desplegar('teoremas')">Teoremas</a></h3>
      
        <ul>
          <%-- <c:forEach items="${categorias}" var="cat"> --%>
            <!--<li style="list-style: none;"><h4>${cat.getNombre()}</h4>-->
              <!--<ul>-->
                 <c:forEach items="${mensaje}" var="teo">
              <%-- <c:choose>
                    <c:when test="${teo.getCategoria().getId()}==${cat.getId()}"> --%>
                    <c:choose>
                      <c:when test="${click.equals(yes)}">
                        <li><a onclick="insertAtCursor('pasoAnt_id', '${teo.getEnunciadoizq()} == ${teo.getEnunciadoder()}')">${teo.getEnunciadoizq()} == ${teo.getEnunciadoder()}</a></li>
                      </c:when>
                    </c:choose>                
                      <li style="list-style: none;" ><a style="text-align: left;">${teo.getEnunciadoizq()} == ${teo.getEnunciadoder()} </a></li>
              <%--  </c:when>
                  </c:choose>--%>
                </c:forEach>
<!--          </ul>
            </li>-->
      <%--</c:forEach> --%>
        </ul>
      </article>     
    </div>
        
    <div style="width: 500px;">
        <h5>${formula}</h5>
    </div>    

    <sf:form action="/Miniproyecto/infer/${usuario.getLogin()}" method="POST" modelAttribute="infer">
      Paso anterior:<br><sf:input path="pasoAnt" id="pasoAnt_id" value="${pasoAnt}"/><sf:errors path="pasoAnt" cssClass="error" />
      <br>
      <!--\cssId{eq}{\style{cursor:pointer;}{p\equiv q}}-->
      Teorema a usar:<br>
       <select style="width: auto; height: auto; border: none;" class="form-control" id="mensaje" name="nStatement">
            <c:forEach items="${mensaje}" var="cat">
              <option value="${cat.getId()}" >${cat.getCategoria().getNombre()} - ${cat.getEnunciadoizq()} == ${cat.getEnunciadoder()}</option>
            </c:forEach>  
            </select>
      <br>
      Instanciación:<br><sf:input path="instanciacion" id="instanciacion_id" value="${instanciacion}"/><sf:errors path="instanciacion" cssClass="error" /></br>
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
