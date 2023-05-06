<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="menu.jsp" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <style>
        table, th, td {
          border: 1px solid black;
        }
        table.center {
          margin-left: auto;
          margin-right: auto;
        }
    </style>

</head>
<body>

<div></div>
    <div class="container">
        <main class="bd-main order-1">
            <div class="d-md-flex flex-md-row align-items-center justify-content-between">
                <h3 class="bd-title" id="content">Projetos</h3>
            </div>
			<c:if test="${not empty listaProjetos}">
                <table>
                    <tr>
                        <th>Nome</th>
                        <th>Orçamento</th>
                        <th>Risco</th>
                        <th>Status</th>
                        <th>Gerente</th>
                        <th>Descrição</th>
                        <th>Data Início</th>
                        <th>Data Fim</th>
                        <th>Data Previsão Fim</th>
                        <th></th>
                        <th></th>
                    </tr>
                	<c:forEach var="projeto" items="${listaProjetos}">
                        <tr>
                            <td>${projeto.nome}</td>
                            <td>${projeto.orcamento}</td>
                            <td>${projeto.risco}</td>
                            <td>${projeto.status}</td>
                            <td>${projeto.nomeGerente}</td>
                            <td>${projeto.descricao}</td>
                            <td>${projeto.dataInicioFormat}</td>
                            <td>${projeto.dataFimFormat}</td>
                            <td>${projeto.dataPrevisaoFimFormat}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/editarProjeto?id=${projeto.id}">
                                   <input type="button" value="Editar" />
                                </a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/excluirProjeto?id=${projeto.id}">
                                   <input type="button" value="Excluir" />
                                </a>
                            </td>
                        </tr>
                	</c:forEach>
            	</table>
        	</c:if>
            
        </main>
    </div>
</body>
</html>