<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="jakarta.tags.core" %>

    <!DOCTYPE html>
    <html>

    <head>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
      <meta charset="UTF-8">
      <title>AppVenda</title>
    </head>
    </body>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
      <div class="container-fluid">
        <ul class="navbar-nav">
          <li class="nav-item"><a class="nav-link active" href="/">AppVenda</a>
          </li>
          <li class="nav-item"><a class="nav-link" href="/vendedor/lista">Vendedores</a>
          </li>
          <li class="nav-item"><a class="nav-link" href="/produto/lista">Produtos</a>
          </li>
          <li class="nav-item"><a class="nav-link" href="/livros/lista">Livros</a></li>
          <li class="nav-item"><a class="nav-link" href="/veiculos/lista">Veículos</a></li>
        </ul>
        <c:if test="${not empty listagem}">
          <form class="d-flex" action="/${rota}/pesquisar">
            <input class="form-control me-2" type="text" name="campoBusca" placeholder="Search">
            <button class="btn btn-primary" type="submit">Search</button>
          </form>
        </c:if>
      </div>
    </nav>

    <div class="container mt-3">

      <span class="badge rounded-pill bg-primary">
        Vendedor: ${qtdeVendedor}
      </span>
      <span class="badge rounded-pill bg-secondary">
        Produto: ${qtdeProduto}
      </span>
      <span class="badge rounded-pill bg-success">
        Livros: ${qtdeLivros}
      </span>
      <span class="badge rounded-pill bg-danger">
        Veículos: ${qtdeVeículos}
      </span>

      <c:if test="${not empty listagem}">
        <h2>AppVenda</h2>
        <p>Gestão de vendas de ${titulo}:</p>
        <table class="table">
          <thead class="table-dark">
            <tr>
              <c:if test="${rota == 'vendedor'}">
                <td>Nome</td>
                <td>Email</td>
                <td>CPF</td>
              </c:if>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="item" items="${listagem}">
              <tr>
                <c:if test="${rota == 'vendedor'}">
                  <td>${item.nome}</td>
                  <td>${item.email}</td>
                  <td>${item.cpf}</td>
                </c:if>
                <td><a href="/${rota}/${item.id}/excluir">excluir</a></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>


      <c:if test="${empty listagem}">
        <hr>
        <form action="/informacao/incluir" method="post">
          <div class="row">
            <div class="col">
              <input type="text" class="form-control" placeholder="Entre com o campo" name="campo">
            </div>
            <div class="col">
              <input type="text" class="form-control" placeholder="Entre com a descrição" name="descricao">
            </div>
            <div class="col">
              <button class="btn btn-primary" type="submit">Cadastrar</button>
            </div>
          </div>
        </form>

        <c:if test="${not empty informacoes}">
          <hr>
          <table class="table">
            <thead class="table-dark">
              <tr>
                <th>Informações:</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="item" items="${informacoes}">
                <tr>
                  <td>${item}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:if>
      </c:if>

      <c:if test="${not empty objeto}">
        <hr>
        <div class="alert alert-success">
          <strong>Sucesso!</strong> ${objeto}
        </div>
      </c:if>
    </div>
    </body>

    </html>