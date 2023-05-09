<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="menu.jsp" %>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
<body>

<div></div>
    <div class="container">
        <main class="bd-main order-1">
            <div class="d-md-flex flex-md-row align-items-center justify-content-between">
                <h3 class="bd-title" id="content">Editar Projeto</h3>
            </div>
            <sf:form action="${pageContext.request.contextPath}/editar" method="post" modelAttribute="projeto">
                <input type="hidden" id="id" name="id" value="${projeto.id}"/>
                <input type="hidden" id="idGerenteAnterior" name="idGerenteAnterior" value="${projeto.idGerenteAnterior}"/>
                <fieldset>
                    <div class="row mb-3">
                            <div class="col">
                                    <label class="form-label">Nome do Projeto</label>
                                    <input id="txtNome" name="nome" value="${projeto.nome}" type="text" class="form-control">

                            </div>
                        <div class="col">
                            <label for="dataInicioInput" class="form-label">Data de Início</label>
                            <input name="dataInicio" type="date" value="${projeto.dataInicio}" class="form-control" id="dataInicioInput">

                        </div>
                        <div class="col">
                            <label for="dataPrevisaoFimInput" class="form-label">Data de Previsão do Fim do Projeto</label>
                            <input name="dataPrevisaoFim" type="date" value="${projeto.dataPrevisaoFim}" class="form-control" id="dataPrevisaoFimInput">

                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <div class="row row mb-3">
                        <div class="col">
                            <label for="dataFimInput" class="form-label">Data Final do Projeto</label>
                            <input name="dataFim" type="date" value="${projeto.dataFim}" class="form-control" id="dataFimInput">

                        </div>
                        <div class="col">
                            <label for="gerenteInput" class="form-label">Gerente</label>
                           	<select id="idGerente" name="idGerente">
			                	<option value="0" selected="selected">----Selecione----</option>
		                    	<c:forEach var="pessoa" items="${listaPessoas}">
                                    <c:choose>
                                        <c:when test="${pessoa.id == projeto.idGerente}">
                                            <option value="${pessoa.id}" selected="selected">
                                                <c:out value="${pessoa.nome}" />
                                            </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${pessoa.id}">
                                                <c:out value="${pessoa.nome}" />
                                            </option>												
                                        </c:otherwise>
                                    </c:choose>	
                                </c:forEach>
			                </select>
                        </div>
                        <div class="col">
                            <label for="statusInput" class="form-label">Status</label>
                      		<select id="idStatus" name="status">
			                	<option value="0" selected="selected">----Selecione----</option>
                                <c:forEach var="status" items="${listaStatus}">
                                    <c:choose>
                                        <c:when test="${status.codigo == projeto.status}">
                                            <option value="${status.codigo}" selected="selected">
                                                <c:out value="${status.descricao}" />
                                            </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${status.codigo}">
                                                <c:out value="${status.descricao}" />
                                            </option>												
                                        </c:otherwise>
                                    </c:choose>	

                                </c:forEach>
			                </select>

                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <div class="row row mb-3">
                        <div class="col">
                            <label for="orcamentoInput" class="form-label">Orçamento</label>
                            <input id="orcamento" type="numeric" name="orcamento" value="${projeto.orcamento}" class="form-control" id="orcamentoInput">

                        </div>
                        <div class="col">
                            <label for="riscoInput" class="form-label">Risco</label>
							<select id="idRisco" name="risco">
                                <option value="0">----Selecione----</option>
                                <c:forEach var="risco" items="${listaRiscos}">                                
                                    <c:choose>
                                        <c:when test="${risco.codigo == projeto.risco}">
                                            <option value="${risco.codigo}" selected="selected">
                                                <c:out value="${risco.descricao}" />
                                            </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${risco.codigo}">
                                                <c:out value="${risco.descricao}" />
                                            </option>												
                                        </c:otherwise>
                                    </c:choose>	
                                
                                </c:forEach>
			                </select>
                        </div>
                        <div class="col">
                            <label for="descricao" class="form-label">Descrição</label>
                            <textarea name="descricao"  id="descricao" type="text" class="form-control" rows="3"><c:out value="${projeto.descricao}" /></textarea>

                        </div>
                    </div>
                </fieldset>
                <div class="d-grid col-2">
                    <button type="submit" class="btn-success">Salvar</button>
                </div>
            </sf:form>
        </main>
    </div>
</body>
</html>