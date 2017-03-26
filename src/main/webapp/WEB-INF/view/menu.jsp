<div class="navbar navbar-default navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
      <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <div class="nav-collapse collapse">
        <ul class="nav">
          <li ${computarMenu}><a href="../../infer/${usuario.login}">Demostrar</a></li>   
          <li ${listarTerminosMenu}><a href="listar?comb=n">Predicados</a></li>
          <li ${guardarMenu}><a href="guardar">Agregar Predicado</a></li>
          <li ${agregarTeoremaMenu}><a href="guardarteo">Agregar Teorema</a></li>
<!--      <li ${verTerminosPublicosMenu}><a href="publico?comb=n">Ver T&eacute;rminos Publicos</a></li>
          <li ${misPublicacionesMenu}><a href="mispublic?comb=n">Mis Publicaciones</a></li>
          <li ${computarMenu}><a href="ingresar">Computar</a></li> -->
          <li ${perfilMenu}><a href="./">Mi Perfil</a></li>
          <li style="float: right;"><a href="close">Cerrar Sesi&oacute;n</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>
<div class="row-fluid" style="margin-left: 50px; margin-top: 69px; height:552px; width: ${anchuraDiv}; overflow-y:${overflow};">