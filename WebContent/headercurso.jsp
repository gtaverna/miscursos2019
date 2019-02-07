<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
					
							<div style="font-size: 120%;" class="col-sm-12 col-xs-12 col-md-12">
									<label class="col-sm-6 col-md-6 col-xs-12">Fecha de curso: ${curso.fecha }</label> 
									<label class="col-sm-6 col-md-6 col-xs-12">Estado: ${curso.estado }</label> 
									<c:if test="${curso.aprobacion != 0 }"><label class="col-sm-6 col-md-6 col-xs-12">%Aprobacion: ${curso.aprobacion }</label> </c:if>
									<label class="col-sm-6 col-md-6 col-xs-12">Tolerancia: ${curso.tolerancia }</label>
									<c:if test="${curso.correlativa != 0 }"><label class="col-sm-6 col-md-6 col-xs-12">Correlativa:	${curso.correlativa }</label> </c:if>
									<c:if test="${curso.presencia != 0 }"><label class="col-sm-6 col-md-6 col-xs-12">Presencia:	${curso.presencia }</label> </c:if>
									<c:if test="${curso.grupo_correlativa != 0 }"><label class="col-sm-6 col-md-6 col-xs-12">G.Correlativa:	${curso.grupo_correlativa }</label>  </c:if>
									<c:if test="${curso.grupo_presencia != 0 }"><label class="col-sm-6 col-md-6 col-xs-12">G.Presencia:	${curso.grupo_presencia }</label><br> </c:if>
										<div class="col-sm-12 col-md-12 col-xs-12"><label>Lugar: </label>${curso.lugar }</div>
									<div class="col-sm-12 col-md-12 col-xs-12"><label>Descripción:</label> ${curso.descripcion }</div>
								
							</div>
					

