$(document).one('ready',function(){

	//var base_url = 'http://localhost:8080/';
	var base_url = 'https://apolus.herokuapp.com/'; 
	var idEstablecimiento = $('#txtIdEstab').val();

	$('.input-number').on('input', function () { 
    	this.value = this.value.replace(/[^0-9]/g,'');
	});


// - - - - - SECCION LISTA AMINALES - - - - -  
	//llenaComboEstadoVaca();
	//llenaCombos($('#txtIdEstab').val());

	$("#chkCategoriaAll").on( 'change', function(e) {
		e.stopImmediatePropagation();
	    if( $(this).is(':checked') ) {
	        // SELECCIONA TODAS LAS CATEGORIAS
	        $("#cboCatAnimales").attr('disabled',true);
	        $("#cboEstadoVacaLista").attr('disabled',true);
	    } else {
	        $("#cboCatAnimales").attr('disabled',false);
	        $("#cboEstadoVacaLista").attr('disabled',false);
	    }
	});

	$('#btnListAnimales').on('click',function(e){
		e.stopImmediatePropagation();
		var cat = $("#cboCatAnimales").val();
		var est = $("#cboEstadoVacaLista").val();
		var url = '';
		var idEstablecimiento = $('#txtIdEstab').val();

		if( $('#chkCategoriaAll').is(':checked')  ) {
		    url = 'animales/list-by-est/'+ idEstablecimiento;

		    $.ajax({
					type: 'get',
					url: base_url + url,
					contentType: "application/json; charset=utf-8",
					//data: {estado: estado},
					success: function(data){
						console.log(data);

						$('#listaVacas th').remove();
						$("#listaVacas thead").append("<th>Diio</th><th>Categoria</th><th>Estado</th><th>Raza</th><th>Resguardo</th><th></th>");
						$('#listaVacas tr').remove();
						$.each(data, function(i, item){
						$('<tr>').html(
	        				"<td>"+data[i].diio +"</td><td>"+ data[i].categoria + "</td><td>" +
	        				 data[i].estadoAnimal + "</td><td>" + data[i].raza + "</td><td>" + data[i].resguardo + "</td>" +
	        				 "<td><button type='button' data-id="+data[i].diio+" data-toggle='modal' data-target='#modalEditVaca' class='btn btn-success' id='btnDetVaca'>Modificar <span class='glyphicon glyphicon-pencil'></span></button></td></tr>").appendTo('#listaVacas');
						});
					},
					error: function(){
						console.log('error al cargar lista de animales');
					}
				});
		}else{
			url = 'animales/list-by-cat-and-est/'+cat+'/'+est+'/'+idEstablecimiento

			if(cat > 0 && est >0){
				//BUSCA SEGUN LOS PARAMETROS
				$.ajax({
					type: 'get',
					url: base_url + url,
					contentType: "application/json; charset=utf-8",
					//data: {estado: estado},
					success: function(data){
						console.log(data);

						$('#listaVacas th').remove();
						$("#listaVacas thead").append("<th>Diio</th><th>Categoria</th><th>Estado</th><th>Raza</th><th>Resguardo</th><th></th>");
						$('#listaVacas tr').remove();
						$.each(data, function(i, item){
						$('<tr>').html(
	        				"<td>"+data[i].diio +"</td><td>"+ data[i].categoria + "</td><td>" +
	        				 data[i].estadoAnimal + "</td><td>" + data[i].raza + "</td><td>"+data[i].resguardo +"</td>" +
	        				 "<td><button type='button' data-id="+data[i].diio+" data-toggle='modal' data-target='#modalEditVaca' class='btn btn-success' id='btnDetVaca'>Modificar <span class='glyphicon glyphicon-pencil'></span></button></td></tr>").appendTo('#listaVacas');
						});
					},
					error: function(){
						console.log('error al cargar lista de animales');
					}
				});
			}else{
				alert('Seleccione las opciones de búsqueda');
			}
		}
	});


	//NUEVA SECCION DE NUEVO ANIMAL
	/*$('#cboCatNuevo').on('change',function(e){
		e.stopImmediatePropagation();
		var cat = $('#cboCatNuevo').val();
		if (cat == 8) {
			$("select#cboSexoNuevo")[0].selectedIndex = 1;
		} else if(cat >= 0 && cat <=3){
			$("select#cboSexoNuevo")[0].selectedIndex = 2;
		}
	});*/

	//GUARDA NUEVO ANIMAL
	$('#btnGuardaNuevo').on('click',function(ev){
		ev.stopImmediatePropagation();
		var idEstablecimiento = $('#txtIdEstab').val();

		if($('#txtDiioNuevo').val() == ''){
			alert('Debe ingresar Diio');
			$('#txtDiioNuevo').focus();
		}else{
			if ( ($('#cboCatNuevo').val() < 1 ) || ($('#cboEstNuevo').val() < 1) || ($('#cboRazaNuevo').val() < 1)) {
				alert('Debe completar todos los datos del formulario');
			}else{

				//VERIFICA SI EL DIIO YA EXISTE
				var diio = $('#txtDiioNuevo').val();
				$.ajax({
					type: 'get',
					url: base_url+'animales/find/'+diio+'/'+idEstablecimiento,
					//data: {diio: diio},
					success: function(data){
						console.log(data);
						if (data != null) {
							alert('El Diio ya se encuentra registrado!');
							$('#txtDiioNuevo').focus();
						}else{
							console.log('no existe');
						}
					},

					//SI NO EXISTE DIIO LO PUEDE GUARDAR
					error: function(jqXHR,xhr, status, text){
						if (jqXHR.status == 404){
						    var formAnimal ={
								'diio' : $('#txtDiioNuevo').val(),
							 	'idCategoria' : $('#cboCatNuevo').val(),
							 	'idEstadoAnimal' : $('#cboEstNuevo').val(),
							 	'idRaza' : $('#cboRazaNuevo').val(),
							 	'fechaNac': $('#txtFnacNuevo').val(),
							 	'fechaIncorporacion' : $('#txtFincNuevo').val(),
							 	'sexo'		: $('#cboSexoNuevo').val(),
							 	'estado': 1,
							 	'idEstablecimiento': idEstablecimiento
							};

							$.ajax({
								type: 'post',
								url: base_url+ 'animales/add',
								data: JSON.stringify(formAnimal),
								contentType: "application/json; charset=utf-8",
								success: function(){
									//alert('Animal registrado exitosamente!');									
						          },
							}).done(function(){
								//INSERTA EN TABLA LOTE_ANIMALES EN LOTE "SIN LOTE"
								//para eso se debe obtener el lote por defecto correspondiente al establecimiento 
								$.ajax({
									type: 'get',
									url: base_url+'lotes/get-lote-defecto/'+idEstablecimiento,
									success: function(data){
										console.log(data);
										var utc = new Date().toJSON().slice(0,10).replace(/-/g,'/');
										var form_loteAnimal = {
											'idLote'  : data[0].idLote,
											'diio': parseInt($('#txtDiioNuevo').val()),
											'vigente' : 1,
											'idEstablecimiento' : idEstablecimiento,
											'fechaAccion' : new Date()
										};

										$.ajax({
											type: 'post',
											url: base_url+'lotes/add-animal-lote',
											data: JSON.stringify(form_loteAnimal),
											contentType: "application/json; charset=utf-8",
											success: function(){
												alert('Animal registrado exitosamente');
												$('#txtDiioNuevo').val('');
											},
											error: function(){
												console.log('error en lote animal');
											}
										});
									},
									error: function(){
										console.log('error al consultar lote defecto');
									}
								});
							});
						}
						
					}
				});
			}
		}
		
	});

	//EDITA ANIMAL
	$('#btnEditVaca').on('click', function(e){
		e.stopImmediatePropagation();

		if (($('#cboCategoriaAnimalEdit').val() < 1 ) || ($('#cboEstadoAnimalEdit').val() < 1)) {
			alert('Debe completar todos los datos del formulario');
		}else{
			var diio = $('#txtDiioVacaEdit').val();

			var formEditVaca = {
				'diio'				: diio,
				'idCategoria' 		: $('#cboCategoriaAnimalEdit').val(),
		 		'idEstadoAnimal' 	: $('#cboEstadoAnimalEdit').val()
			};

			var form_nuevo = {
				'diio'				: diio,
				'idCategoria' 		: $('#cboCategoriaAnimalEdit').val(),
		 		'idEstadoAnimal' 	: $('#cboEstadoAnimalEdit').val(),
				'idRaza'			: $('#cboRazaVacaEdit').val()
			}


		 	$.ajax({
		 		type: 'put',
		 		url: base_url+"animales/"+diio+"/"+idEstablecimiento,
		 		data: JSON.stringify(formEditVaca),
				contentType: "application/json; charset=utf-8",
				success: function(){

				},
				error: function(){
					$('#modalEditVaca').modal('hide');
						console.log('Vaca modificada')
						//window.location.reload(); -- se debe modificar para volver a dibujar tabla mediante ajax
				}
		 	}).done(function(){
				$.ajax({
					type: 'post',
					url: base_url +"animales/nuevo-edita/"+diio+"/"+idEstablecimiento,
					data: JSON.stringify(form_nuevo),
					contentType: "application/json; charset=utf-8",
					success: function(){
						$('#modalEditVaca').modal('hide');
						$('body').removeClass('modal-open');
						$('.modal-backdrop').remove();

						//RECARGA TABLA LISTA VACAS
						var cat = $("#cboCatAnimales").val();
						var est = $("#cboEstadoVacaLista").val();
						var url = '';
						var idEstablecimiento = $('#txtIdEstab').val();

						if( $('#chkCategoriaAll').is(':checked')  ) {
							url = 'animales/list-by-est/'+ idEstablecimiento;

							$.ajax({
									type: 'get',
									url: base_url + url,
									contentType: "application/json; charset=utf-8",
									//data: {estado: estado},
									success: function(data){
										console.log(data);

										$('#listaVacas th').remove();
										$("#listaVacas thead").append("<th>Diio</th><th>Categoria</th><th>Estado</th><th>Raza</th><th>Resguardo</th><th></th>");
										$('#listaVacas tr').remove();
										$.each(data, function(i, item){
										$('<tr>').html(
											"<td>"+data[i].diio +"</td><td>"+ data[i].categoria + "</td><td>" +
											data[i].estadoAnimal + "</td><td>" + data[i].raza + "</td><td>" + data[i].resguardo + "</td>" +
											"<td><button type='button' data-id="+data[i].diio+" data-toggle='modal' data-target='#modalEditVaca' class='btn btn-success' id='btnDetVaca'>Modificar <span class='glyphicon glyphicon-pencil'></span></button></td></tr>").appendTo('#listaVacas');
										});
									},
									error: function(){
										console.log('error al cargar lista de animales');
									}
								});
						}else{
							url = 'animales/list-by-cat-and-est/'+cat+'/'+est+'/'+idEstablecimiento

							if(cat > 0 && est >0){
								//BUSCA SEGUN LOS PARAMETROS
								$.ajax({
									type: 'get',
									url: base_url + url,
									contentType: "application/json; charset=utf-8",
									//data: {estado: estado},
									success: function(data){
										console.log(data);

										$('#listaVacas th').remove();
										$("#listaVacas thead").append("<th>Diio</th><th>Categoria</th><th>Estado</th><th>Raza</th><th>Resguardo</th><th></th>");
										$('#listaVacas tr').remove();
										$.each(data, function(i, item){
										$('<tr>').html(
											"<td>"+data[i].diio +"</td><td>"+ data[i].categoria + "</td><td>" +
											data[i].estadoAnimal + "</td><td>" + data[i].raza + "</td><td>"+data[i].resguardo +"</td>" +
											"<td><button type='button' data-id="+data[i].diio+" data-toggle='modal' data-target='#modalEditVaca' class='btn btn-success' id='btnDetVaca'>Modificar <span class='glyphicon glyphicon-pencil'></span></button></td></tr>").appendTo('#listaVacas');
										});
									},
									error: function(){
										console.log('error al cargar lista de animales');
									}
								});
							}else{
								alert('Seleccione las opciones de búsqueda');
							}
						}

					},
					error: function(){
						console.log('error al insertar nuevo');
					}
				});
			 });
		}
	});

	function llenaCboCategoria(nombreCombo,idEstablecimiento){
		$.ajax({
			type: 'get',
			url: base_url + 'categoria/all/'+idEstablecimiento,
			success: function(data){
				$(nombreCombo).empty();
				$.each(data,function(key, registro) {
			        $(nombreCombo).append('<option value="0" selected disabled hidden>Seleccione</option>'+'<option value='+registro.idCategoriaAnimal+'>'+registro.descripcion+'</option>');
			    });        
			},
		});
	}

	//carga combo categorias del modal editar animalm este combo no tiene el item "seleccione" por defecto
	function llenaCboCategoria2(nombreCombo,idEstablecimiento){
		$.ajax({
			type: 'get',
			url: base_url + 'categoria/all/'+idEstablecimiento,
			success: function(data){
				$(nombreCombo).empty();
				$.each(data,function(key, registro) {
			        $(nombreCombo).append('<option value='+registro.idCategoriaAnimal+'>'+registro.descripcion+'</option>');
			    });        
			},
		});
	}

	function llenaCboEstado(nombreCombo){
		$.ajax({
			type: 'get',
			url:  base_url + 'estado/all-estados',
			success: function(data){
				$(nombreCombo).empty();
				$.each(data,function(key, registro) {
			        $(nombreCombo).append('<option value="0" selected disabled hidden>Seleccione</option>'+'<option value='+registro.idEstadoAnimal+'>'+registro.descripcion+'</option>');
			    });        
			},
		});
	}

	function llenaCboEstado2(nombreCombo,idEstablecimiento){
		$.ajax({
			type: 'get',
			url:  base_url + 'estado/all-estados/'+idEstablecimiento,
			success: function(data){
				$(nombreCombo).empty();
				$.each(data,function(key, registro) {
			        $(nombreCombo).append('<option value='+registro.idEstadoAnimal+'>'+registro.descripcion+'</option>');
			    });        
			},
		});
	}

	function llenaCboRaza(nombreCombo){
		$.ajax({
			type: 'get',
			url:  base_url + 'raza/all-razas/'+idEstablecimiento,
			success: function(data){
				$(nombreCombo).empty();
				$.each(data,function(key, registro) {
			        $(nombreCombo).append('<option value="0" selected disabled hidden>Seleccione</option>'+'<option value='+registro.idRaza+'>'+registro.descripcion+'</option>');
			    });        
			},
		});
	}

	function llenaCboRaza2(nombreCombo){
		$.ajax({
			type: 'get',
			url:  base_url + 'raza/all-razas/'+idEstablecimiento,
			success: function(data){
				$(nombreCombo).empty();
				$.each(data,function(key, registro) {
			        $(nombreCombo).append('<option value='+registro.idRaza+'>'+registro.descripcion+'</option>');
			    });        
			},
		});
	}

	function llenaCboTipoParto(nombreCombo){
		$.ajax({
			type: 'get',
			url:  base_url + 'tipo-parto/all',
			success: function(data){
				$(nombreCombo).empty();
				$.each(data,function(key, registro) {
			        $(nombreCombo).append('<option value="0" selected disabled hidden>Seleccione</option>'+'<option value='+registro.idTipoParto+'>'+registro.descripcion+'</option>');
			    });        
			},
		});
	}

	function llenaComboEstadoVaca(){
		llenaCboEstado("#cboEstadoVacaLista");
	}

	function llenaCombos(){
		//llenaCboCategoria("#cboCategoriaAnimal");
		llenaCboCategoria("#cboCatAnimales", idEstablecimiento);
		llenaCboEstado("#cboEstadoAnimal");
		llenaCboRaza("#cboRazaVaca");
	}

	function llenaCombosEdit(){
		llenaCboCategoria2("#cboCategoriaAnimalEdit",idEstablecimiento);
		llenaCboEstado2("#cboEstadoAnimalEdit", idEstablecimiento);
		llenaCboRaza2("#cboRazaVacaEdit");
	}

	function llenaCombosParto(){
		llenaCboRaza('#cboRazaTerneroParto');
		llenaCboTipoParto('#cboTipoParto');
	}

	$("#modalNewParto" ).on('shown.bs.modal', function(e){
		e.preventDefault();
	    e.stopImmediatePropagation();
	    llenaCombosParto();
	});

	$("#modalNewVaca" ).on('shown.bs.modal', function(e){
		e.preventDefault();
	    e.stopImmediatePropagation();
	    llenaCombos();
	});

	$("#modalPesaje" ).on('shown.bs.modal', function(e){
		e.preventDefault();
	    e.stopImmediatePropagation();
	    llenaCboCategoria('#cboCategoriaPesaje2');
	});

	$("#modalEditVaca" ).on('shown.bs.modal', function(e){
		e.preventDefault();
	    e.stopImmediatePropagation();
	    llenaCombosEdit();

	    var boton = e.relatedTarget;
		var diio_vaca2 = $(boton).attr('data-id');
		console.log(diio_vaca2);
		$('#txtDiioVacaEdit').val(diio_vaca2);
		var diio = diio_vaca2;
		var idEstablecimiento = $('#txtIdEstab').val();

		//carga los datos de la vaca seleccionada de la lista
		$.ajax({
			type: 'get',
			url: base_url +"animales/find/"+ diio+"/"+idEstablecimiento,
			success: function(data){
				console.log(data.idEstadoAnimal);
				var estado = data.idEstadoAnimal;
				var categoria = data.idCategoria;
				var raza = data.idRaza;
				$('#cboEstadoAnimalEdit').val(estado);
				$('#cboCategoriaAnimalEdit').val(categoria);
				$('#cboRazaVacaEdit').val(raza);
			}
		});
	});

	$("#modalNewVaca").on("hidden.bs.modal", function () {
	    $('#cboCategoriaAnimal').children().remove().end()
	});

	//GUARDA NUEVO PARTO
	$('#btnGuardaParto').on('click', function(e){			
		e.stopImmediatePropagation()
		
		if(($('#txtDiioVacaParto').val() < 1) || ($('#txtNuevoTernero').val() < 1) || $('#cboRazaTerneroParto').val() < 1 || $('#cboTipoParto').val() < 1 || $('#txtFechaParto').val() == ""){
			alert('Debe completar todos los datos del formulario!');
		}else{

			//valida si la vaca existe buscando por DIIO,categoria ternero(7) y establecimiento
			var diio = $('#txtDiioVacaParto').val();
			var idEstablecimiento = $('#txtIdEstab').val();
				$.ajax({
					type: 'get',
					url: base_url + 'animales/find/'+diio+'/'+idEstablecimiento,
					data: {diio: diio},
					success: function(data){
						console.log(data);
						if (data != null) {
							//SI LA VACA EXISTE VALIDA SI EXISTE el DIIO DEL TERNERO
							var diioCria = $('#txtNuevoTernero').val();
							$.ajax({
								type:'get',
								url: base_url + 'animales/find/'+diioCria+'/'+idEstablecimiento,
								success: function(data){
									//valida si el diio del ternero ya existe
									if (data != null) {
										alert('El Diio del ternero ya existe');
										$('#txtNuevoTernero').focus();
									}
								},
								error: function(){
									
									//si ternero no existe puede guardar todos los datos del parto
									var formParto = {
										"fecha" 		: $('#txtFechaParto').val(),
										"idVaca"		: $('#txtDiioVacaParto').val(),
										"idCria"		: $('#txtNuevoTernero').val(),
										"observaciones" : $('#txtObsParto').val(),
										"idTipoParto"	: $('#cboTipoParto').val(),
										"sexo"          : $('#cboSexo').val(),
										"idEstablecimiento" : idEstablecimiento
									};

									//guarda nuevo parto
									$.ajax({
										type:'post',
										url: base_url+'partos/new',
										data: JSON.stringify(formParto),
										contentType: "application/json; charset=utf-8",
										success: function(){
											//guarda el nuevo ternero
											var formCria = {
												"fechaNac"  : $('#txtFechaParto').val(),
												"diio"  : $('#txtNuevoTernero').val(),
												"idCategoria": 0, //CATEGORIA DE TERNERO POR DEFECTO
												"idEstadoAnimal" : 0, // ESTADO N/A POR DEFECTO
												"sexo"      : $('#cboSexo').val(),
												"estado"	: 1,
												"idRaza"	: $('#cboRazaTerneroParto').val(),
												"fechaIncorporacion" : $('#txtFechaParto').val(),
												"idEstablecimiento" : idEstablecimiento
											}

											$.ajax({
												type: 'post',
												url: base_url+'animales/add',
												data: JSON.stringify(formCria),
												contentType: "application/json; charset=utf-8",
												success: function(){
													console.log('ternero guardado');
												},
												error: function(){
													console.log('error al guardar ternero');
												}
											}).done(function(){
													//INSERTA EN TABLA LOTE_ANIMALES EN LOTE "SIN LOTE"
													//para eso se debe obtener el lote por defecto correspondiente al establecimiento 
													$.ajax({
														type: 'get',
														url: base_url+'lotes/get-lote-defecto/'+idEstablecimiento,
														success: function(data){
															var utc = new Date().toJSON().slice(0,10).replace(/-/g,'/');
															var form_loteAnimal = {
																'idLote'  : data[0].idLote,
																'diio': parseInt($('#txtNuevoTernero').val()),
																'vigente' : 1,
																'idEstablecimiento' : idEstablecimiento,
																'fechaAccion' : new Date()
															};

															$.ajax({
																type: 'post',
																url: base_url+'lotes/add-animal-lote',
																data: JSON.stringify(form_loteAnimal),
																contentType: "application/json; charset=utf-8",
																success: function(){
																	$('#modalNewParto').modal('hide');
																	$('body').removeClass('modal-open');
																	$('.modal-backdrop').remove();
																},
																error: function(){
																	console.log('error en lote animal');
																}
															});
														},
														error: function(){
															console.log('error al consultar lote defecto');
														}
													});
												});
											

											//$('#modalNewParto').modal('hide');
										    //window.location.reload();
										},
										error: function(){
											console.log('error al registrar parto');
										}		
									});
								}		
							});

						}else{
							//si la vaca no existe
							console.log('La vaca indicada no existe');
						}
					},
					error: function(){
						alert('La vaca indicada no existe');
						$('#txtDiioVacaParto').focus();
					}
				});
		}
	});

	//bucar partos

	$("#chkPartosAll").on( 'change', function(e) {
		e.stopImmediatePropagation();
		if( $(this).is(':checked') ) {
			// SELECCIONA TODAS LAS CATEGORIAS
			$("#cboTipoParto2").attr('disabled',true);
		} else {
			$("#cboTipoParto2").attr('disabled',false);
		}
	});

	$('#btnBuscaPartos').on('click',function(e){
		e.stopImmediatePropagation();
		var fini = $('#txtFechaIniParto').val();
		var ffin = $('#txtFechaFinParto').val();
		var tipo = $('#cboTipoParto2').val();
		var url = '';
		var idEstablecimiento = $('#txtIdEstab').val();

		if($('#chkPartosAll').is(':checked') ) {
			url = 'all-partos/'
		}else{
			url = 'all/'
		}

		$.ajax({
			type:'get',
			url: url,
			data: {fini: fini, ffin: ffin, tipoParto: tipo, idEstablecimiento: idEstablecimiento},
			contentType: "application/json; charset=utf-8",
			success: function(data){
				$('#listaPartos th').remove();
					$("#listaPartos thead").append("<th>Fecha</th><th>Diio Vaca</th><th>Diio Ternero</th><th>Raza</th><th>Sexo</th><th>Tipo</th>");
					$('#listaPartos tr').remove();
					$.each(data, function(i, item){
					$('<tr>').html(
        				"<td>"+data[i].fecha +"</td><td>"+ data[i].vaca + "</td><td>" +
        				 data[i].ternero + "</td><td>" + data[i].raza + 
        				 "</td><td>"+data[i].sexo+"</td><td>"+data[i].tipo+"</td></tr>").appendTo('#listaPartos');
					});
			},
			error: function(){
				console.log('error al buscar partos');
			}
		});
	});

	//PESAJE
	$('#btnGuardaPesaje').on('click',function(e){
		e.stopImmediatePropagation();
		//VALIDA QUE EXISTA EL DIIO DE VACA O TERNERO
		var diio = $('#txtDiioPesaje').val();
		var cat = $('#cboCategoriaPesaje2').val();
		var ultPeso = '';
		var idEstablecimiento = $('#txtIdEstab').val();
		
			//busca el ultimo peso registrado
			$.ajax({
				type: 'get',
				url: base_url + 'pesaje/get-ultimo/'+diio+'/'+idEstablecimiento,
				success: function(data){
					console.log('ultimo peso: ');
					if(data.length < 1){
						ultPeso = 0;
					}else{
						ultPeso = data[0].peso;
					}

					var formPesaje = {
						'fecha'	   : $('#txtFechaPesaje').val(),
						'diio' : diio,
						'peso'     : $('#txtPeso').val(),
						'ganado'   : $('#txtPeso').val() - ultPeso,
						'observaciones' : $('#txtObsPesaje').val(),
						'idEstablecimiento': idEstablecimiento
					};

					$.ajax({
						type: 'get',
						url: base_url + 'animales/find/' +diio+'/'+idEstablecimiento,
						success: function(data){
							if (data != null) {
								//si el diio existe puede guardar el pesaje
								$.ajax({
									type: 'post',
									url: base_url + 'pesaje/add',
									data: JSON.stringify(formPesaje),
									contentType: "application/json; charset=utf-8",
									success: function(){
										$('#modalPesaje').modal('hide');
										$('body').removeClass('modal-open');
										$('.modal-backdrop').remove();
									},
									error: function(){
										console.log('error al registrar pesaje');
									}
								});
							}

						},
						error: function(){
							alert('El DIIO no está registrado');
						}
					});
				},
				error: function(){
					console.log('error al traer ultPeso')
				}

			});
		
	});

	//lista de historial de pesaje
	$("#chkPesajeAll").on( 'change', function(e) {
		e.stopImmediatePropagation();
		if( $(this).is(':checked') ) {
			// SELECCIONA TODAS LAS CATEGORIAS
			$("#cboCategoriaPesaje").attr('disabled',true);
		} else {
			$("#cboCategoriaPesaje").attr('disabled',false);
		}
	});

	$('#btnBuscaPesaje').on('click',function(e){
		e.stopImmediatePropagation();
		var idCategoria = $('#cboCategoriaPesaje').val();
		var fini = $('#txtFechaIniPesaje').val();
		var ffin = $('#txtFechaFinPesaje').val();
		var idEstablecimiento = $('#txtIdEstab').val();
		var request;

		if ($('#chkPesajeAll').is(':checked')) {
			request = 'pesaje/pesajes-categoria-todos/'+idEstablecimiento;
		}else{
			request = 'pesaje/pesajes-categoria/'+idCategoria+'/'+idEstablecimiento;
		}
		
		$.ajax({
			type:'get',
			url: base_url+ request,
			data: {fini: fini, ffin: ffin},
			success: function(data){
				console.log(data);
				$('#listaPesaje th').remove();
					$("#listaPesaje thead").append("<th>Diio</th><th>Peso</th><th>Ganado</th><th>Fecha</th><th>Sexo</th>");
					$('#listaPesaje tr').remove();
					$.each(data, function(i, item){
					$('<tr>').html(
        				"<td>"+data[i].diio +"</td><td>"+ data[i].peso + "</td><td>" +
        				 data[i].ganado + "</td><td>" + data[i].fecha + "</td><td>"+
        				 data[i].sexo + "</td><td><a href="+base_url+"pesaje/chart/"+data[i].diio + "/"+idEstablecimiento+" target='blank' class='btn btn-info'><span class='glyphicon glyphicon-stats'></span> Gráfico</a></td></tr>").appendTo('#listaPesaje');
					});
			},
			error: function(){
				console.log('error al buscar pesajes');
			}
		});
	});

	// - - - - - LOTES - - - - - - - -
	$('#btnNuevoLote').on('click',function(e){
		e.stopImmediatePropagation();
		var idEstablecimiento = $('#txtIdEstab').val();

		if ($('#txtLote').val() == '') {
			alert('Ingrese nombre del lote');
			$('#txtLote').focus();

		}else{
			var formLote = {
				'descripcion' : $('#txtLote').val(),
				'idEstablecimiento': idEstablecimiento,
				'vigente': 1
			};

			$.ajax({
				type:'post',
				url: base_url+'lotes/add',
				data: JSON.stringify(formLote),
				contentType: "application/json; charset=utf-8",
				success: function(){
					alert('Lote creado exitosamente!');
					window.location.reload();
				},
				error: function(){
					console.log('Error al crear lote');
				}
			});
		}
	});

	$('#modalLoteEdit').on('shown.bs.modal', function (e) {
		e.stopImmediatePropagation();
		var boton = e.relatedTarget;
		var idLote_ = $(boton).attr("data-id");
		$('#txtHiddenLote').val(idLote_);
	});

	$('#btnLoteEdit').on('click',function(e){
		e.stopImmediatePropagation();
		var idLote = $('#txtHiddenLote').val();
		var idEstablecimiento = $('#txtIdEstab').val();
		
		if($('#txtLoteEdit').val() == ''){
			alert('Debe ingresar nombre');
			$('#txtLoteEdit').focus()
		}else{
			var formLote = {
				'descripcion' : $('#txtLoteEdit').val(),
				'idEstablecimiento': idEstablecimiento
			};

			$.ajax({
				type: 'put',
				url: base_url + 'lotes/'+idLote,
				data: JSON.stringify(formLote),
				contentType: "application/json; charset=utf-8",
				success: function(){
					alert('El nombre del lote fue modificado!');
					window.location.reload();
				},
				error: function(){
					console.log('error al modificar nombre');
				}
			});
		}
	});


	//- - - - - -  ASIGNAR ANIMALES A LOTES - - - - 
	$("#cboLoteAsignar").change(function(e){
		e.stopImmediatePropagation();
		var cat = $("#cboLoteAsignar").val();
	});

	$('#btnAddAnimalLote').on('click',function(e){
		e.stopImmediatePropagation();
		var diio = $('#txtLoteAnimal').val();
		var idLote = $('#hiddenIdLote').val();
		var idEstablecimiento = $('#txtIdEstab').val();

		if (diio == '') {
			alert('Ingrese Diio');
			$('#txtLoteAnimal').focus();
		}else{
			//VALIDA SI EL DIIO EXISTE
			$.ajax({
				type: 'get',
				url: base_url+'animales/find/'+diio+'/'+idEstablecimiento,
				success: function(data){
					console.log(data);
					if(data == ''){
						alert('El Diio no fue encontrado');
					}else{
						//VALIDA SI EL ANIMAL PERTENECE A ALGUN LOTE
						$.ajax({
							type: 'get',
							url: base_url+'lotes/valida-animal-lote/'+idLote+'/'+diio+'/'+idEstablecimiento,
							success: function(data){
								console.log(data);
								if(data == ''){
									//Puede asigarle un lote
									//alert('El animal no pertenece a ningun lote');
									var animal_lote = {
										'idLote' : idLote,
										'diio'   : diio,
										'vigente': 1,
										'idEstablecimiento' : idEstablecimiento,
										'fecha_accion' : new Date()
									}

									$.ajax({
										type: 'post',
										url: base_url + 'lotes/add-animal-lote',
										data: JSON.stringify(animal_lote),
										contentType: "application/json; charset=utf-8",
										success: function(){
											alert('animal asignado al lote');
											window.location.reload();
										},
										error: function(){
											console.log('error al asignar lote');
										}
									});
								}else{
									alert('el animal pertenece al lote '+ data[0].lote);
								}
							},
							error: function(){
								console.log('error al validar animal-lote');
							}
						});
					}
				},
				error: function(){
					alert('El Diio no fue encontrado');
					$('#txtLoteAnimal').focus();
				}
			});
		}
	});

	//LISTA DE ANIMALES PARA ASIGNAR LOTE
	$('#btnLotesBusca').on('click',function(e){
		e.stopImmediatePropagation();
		var idLote = $('#cboLoteAsignar').val();
		var idLoteActual = $('#cboLoteBusca').val();
		var idEstablecimiento = $('#txtIdEstab').val();

		if(idLoteActual == null){
			alert('Seleccione un lote');
		}else{
			
			$.ajax({
				type: 'get',
				url: base_url + 'animales/get-by-lote-and-est/'+idLoteActual+'/'+idEstablecimiento,
				success: function(data){
					console.log(data);
					$('#tablaAnimalesLotes th').remove();
					$("#tablaAnimalesLotes thead").append("<th>Diio</th><th>Sexo</th><th>Fecha</th><th></th>");
					$('#tablaAnimalesLotes tr').remove();
					$.each(data, function(i, item){
					$('<tr>').html(
						"<td>"+data[i].diio +"</td><td>"+ data[i].sexo + "</td>" +
						"<td>" + data[i].fecha+ "</td>"+
        				 "<td><input type='checkbox' value="+data[i].diio+ " name='chk' id='chkBox" + i + "' /></td></tr>").appendTo('#tablaAnimalesLotes');
					});
				},
				error: function(){
					console.log('error al cargar lista de animales lote');
				}
			});
		}
	});

	//BOTON  ASIGNA ANIMALES SELECCIONADOS AL NUEVO LOTE
	$('#btnLoteAsigna').on('click',function(e){
		e.stopImmediatePropagation();
		
		if($('#cboLoteAsignar').val() == null){
			alert('Seleccione un lote');
		}else{
			alertify.confirm("Está seguro que desea cambiar a los animales de lote?",
			function() {
				
					//alertify.success('Si');

					var checkIDs = $("input:checkbox:checked").map(function(){
						return parseInt(this.value);
					}).toArray();
					
					//console.log(JSON.stringify(checkIDs));
					var idEstablecimiento = $('#txtIdEstab').val();
					//dejar los registros de lote_animales, campo vigente=0
					$.ajax({
						type: 'put',
						url: base_url +'lotes/upd-vigente/'+idEstablecimiento,
						data:JSON.stringify(checkIDs),
						contentType: "application/json; charset=utf-8",
						success: function(){
							console.log('registros actualizados');
							
							var i = 0;

							for(i of checkIDs){
								
								var loteNew = $('#cboLoteAsignar').val();
								var form_animalLote = {
									'idLote' 	: loteNew,
									'diio'		: i,
									'vigente'	: 1,
									'idEstablecimiento' : idEstablecimiento,
									'fechaAccion' 		: new Date()
								}
								console.log(i);
								
								$.ajax({
									type: 'post',
									url: base_url +'lotes/add-animal-lote/',
									data:JSON.stringify(form_animalLote),
									contentType: "application/json; charset=utf-8",
									success: function(){
										console.log('registros insertados');
										var idLoteActual = $('#cboLoteBusca').val();

										$.ajax({
											type: 'get',
											url: base_url + 'animales/get-by-lote-and-est/'+idLoteActual+'/'+idEstablecimiento,
											success: function(data){
												console.log(data);
												$('#tablaAnimalesLotes th').remove();
												$("#tablaAnimalesLotes thead").append("<th>Diio</th><th>Sexo</th><th>Fecha</th><th></th>");
												$('#tablaAnimalesLotes tr').remove();
												$.each(data, function(i, item){
												$('<tr>').html(
													"<td>"+data[i].diio +"</td><td>"+ data[i].sexo + "</td>" +
													"<td>" + data[i].fecha+ "</td>"+
													 "<td><input type='checkbox' value="+data[i].diio+ " name='chk' id='chkBox" + i + "' /></td></tr>").appendTo('#tablaAnimalesLotes');
												});
											},
											error: function(){
												console.log('error al cargar lista de animales lote');
											}
										});
									},
									error: function(){
										console.log('error insertando registros '+ JSON.stringify(checkIDs) );
									}
								});
								
							}
						},
						error: function(){
							console.log('error actualizando registros '+ JSON.stringify(checkIDs) );
						}
					});
				},
				function() {
					//alertify.error('No');
					console.log('no');
				}
			).set({title:"Gestión Ganadera"}).set({labels:{ok:'Si', cancel: 'No'}});

		}
	});

	//- - - - - TRATAMIENTOS - - - - - -
	//INDEX
	$('#btnTtoBusca').on('click',function(e){
		e.stopImmediatePropagation();
		var diio = $('#txtTtoDiioIndex').val();
		var idEstablecimiento = $('#txtIdEstab').val();

		//valida si el animal existe
		$.ajax({
			type: 'get',
			url: base_url + 'animales/find/'+diio+'/'+idEstablecimiento,
			success: function(data){
				//busca tratamientos del animal
				$.ajax({
					type: 'get',
					url: base_url+'tratamientos/find-by-diio/'+diio+'/'+idEstablecimiento,
					success: function(data){
						$('#listaTtos th').remove();
						$('#listaTtos tr').remove();
						$("#listaTtos thead").append("<th>Diio</th><th>Raza</th><th>Estado</th><th>Fecha</th><th>Tratamiento</th><th>Dias Resguardo</th>");
						$('#tablaAnlistaTtosimalesLotes tr').remove();
						$.each(data, function(i, item){
						$('<tr>').html(
							"<td>"+data[i].diio +"</td>"+ 
							"<td>"+ data[i].raza + "</td>" + 
							"<td>"+ data[i].estadoAnimal + "</td>" + "<td>"+data[i].fecha + "</td>" +
							"<td>"+data[i].tratamiento + "</td><td>" + data[i].resguardo+ "</td>"+
							"</tr>").appendTo('#listaTtos');
						});
					}
				})
			},
			error: function(){
				alert('El Diio no es válido');
				$('#txtTtoDiioIndex').focus();
			}
		});
	});

	$(function() {
		$("#txtFechaTto").datepicker({ dateFormat: 'yy-mm-dd'}); 
   });

	$('#btnOkTto').on('click',function(e){
		e.stopImmediatePropagation();
		var diio = $('#txtDiioTto').val();
		var descripcion = $('#txtDescTto').val();
		var dias = '';
		var fecha_desde = '';
		var fecha_hasta = '';
		var idEstablecimiento = $('#txtIdEstab').val();

		if(diio == '' || descripcion == ''){
			alert('Debe completar todos los datos');
			$('#txtDiioTto').focus();
		}else{
			if ($('#checkResguardo').is(':checked')) {
				dias = $('#txtPeriodoTto').val();
				fecha_desde = $('#txtFechaTto').val();
			}

			Date.prototype.yyyymmdd = function() {
				var mm = this.getMonth() + 1;
				var dd = this.getDate();
			  
				return [this.getFullYear(),
						(mm>9 ? '' : '0') + mm,
						(dd>9 ? '' : '0') + dd
					   ].join('-');
			  };

			  var days = document.getElementById("txtPeriodoTto").value;
			  var date = new Date(document.getElementById("txtFechaTto").value);
			  date.setDate(date.getDate() + parseInt(days));
			  fecha_hasta = date.yyyymmdd();

			//valida si el animal existe
			$.ajax({
				type: 'get',
				url: base_url + 'animales/find/'+diio+'/'+idEstablecimiento,
				success: function(data){
					
					
					var form_tto = {
						'fecha' 	 : new Date(),
						'diio'  	 : diio,
						'descripcion': descripcion,
						'fechaDesde': fecha_desde,
						'fechaHasta': fecha_hasta,
						'vigente'	 : 1,
						'idEstablecimiento' : idEstablecimiento
					}
					console.log(form_tto);
					//GUARDA TRATAMIENTO
					$.ajax({
						type: 'post',
						url: base_url + 'tratamientos/add',
						data: JSON.stringify(form_tto),
						contentType: "application/json; charset=utf-8",
						success: function(){
							alert('Trtatamiento registrado exitosamente');
							$('#txtDiioTto').val('');
							$('#txtDescTto').val('');
							$('#txtPeriodoTto').val('');
							$('#txtDiioTto').focus();
						},
						error: function(){
							console.log('error registrando tratamiento');
						}
					});
				},
				error: function(){
					alert('El Diio no es válido');
					$('#txtDiioTto').focus();
				}
			});
		}
	});

	$('#checkResguardo').change(function(e){
		e.stopImmediatePropagation();
		if ($('#checkResguardo').is(':checked')) {
			$('#txtPeriodoTto').removeAttr('readonly');
			$('#txtFechaTto').removeAttr('readonly');
		}else{
			$('#txtPeriodoTto').attr('readonly', 'readonly');
			$('#txtFechaTto').attr('readonly', 'readonly');
		}
	});

	//NUEVO ESTABLECMIENTO - USUARIO
	$('#nuevoEstab').on('click', function(e){
		e.stopImmediatePropagation();

		$('#modalNewEstab').modal('show');
	});

	$('#btnGuardaEstab').on('click', function(e) {		
		form_data = {
			'descripcion' : $('#txtNuevoEstab').val(),
			'vigente': 1
		};

		//'idusuario' : $('#hiddenUsu2').val(),

		$.ajax({
			type: 'post',
			url: base_url +'establecimiento/add',
			data: JSON.stringify(form_data),
			contentType: "application/json; charset=utf-8",
			success: function(data){
				console.log('nuevo estab: ' + JSON.stringify(data));
				$('#idestab').val(data.idEstablecimiento);

				$.ajax({
					type: 'post',
					url: base_url+'establecimiento/usu-est',
					contentType: "application/json; charset=utf-8",
					data: JSON.stringify({idestablecimiento:$('#idestab').val(),idusuario: $('#hiddenUsu2').val() }),
					success: function(){
						$('#modalNewEstab').modal('hide');
						console.log('usuario-estab ok');

						//CREA LOTE POR DEFECTO : SIN LOTE
						var form_lote = {
							'descripcion' 		: 'Sin Lote',
							'fechaCreacion' 	: new Date(),
							'idEstablecimiento' : $('#idestab').val(),
							'vigente'			: 1
						}

						$.ajax({
							type: 'post',
							url: base_url+'lotes/add',
							contentType: "application/json; charset=utf-8",
							data: JSON.stringify(form_lote),
							success: function(){
								console.log('lote x defecto ok');
							},
							error: function(){
								console.error('error al crear lote x defecto');
							}
						});
					},
					error: function(){
						console.error('error al crear usu-est');
					}
				});
			},
			error: function(){
				console.log('error al crear establecimiento');
			}
		});
	});

	//- - - - CONFIG  - - - - -
	$('#btnGuardaCategoria').on('click',function(e){
		e.stopImmediatePropagation();
		var idEstablecimiento = $('#txtIdEstab').val();

		var form_cat = {
			'descripcion' : $('#txtNuevaCat').val(),
			'idestablecimiento' : idEstablecimiento
		}

		$.ajax({
			type:'post',
			url: base_url+'categoria/save',
			data: JSON.stringify(form_cat),
			contentType: "application/json; charset=utf-8",
			success: function(){
				$.ajax({
					type:'get',
					url: base_url + 'categoria/all/'+idEstablecimiento,
					success: function(data){
						console.log(data);
						$('#listaCategorias th').remove();
							$("#listaCategorias thead").append("<th style='display:none;'>Id</th><th>Categoria</th><th><button type='button' id='addCat' class='btn btn-primary' data-toggle='modal' data-target='#modalNewCat'> <span class='glyphicon glyphicon-plus'></span> </button></th>");
							$('#listaCategorias tr').remove();
							$.each(data, function(i, item){
							$('<tr>').html(
								"<td  style='display:none;'>"+data[i].idCategoria +"</td><td>"+data[i].descripcion+"</td>" +
								"<td><a href=#" +" target='blank' class='btn btn-danger'> <span "+"class='glyphicon glyphicon-remove'></span> </a></td></tr>").appendTo('#listaCategorias');
							});
					},
					error: function(){
						console.log('error al buscar categorias');
					}
				});

				$('#modalNewCat').modal('hide');
				$('.modal-backdrop').remove();
			},
			error: function(){
				console.log('Error al crear categoria');
			}
		});
	
	});

		
		$('#btnGuardaEstado').on('click',function(e){
			e.stopImmediatePropagation();
			var idEstablecimiento = $('#txtIdEstab').val();
	
			var form_est = {
				'descripcion' : $('#txtNuevoEst').val(),
				'idEstablecimiento' : idEstablecimiento
			}

			
		$.ajax({
			type:'post',
			url: base_url+'estado/save',
			data: JSON.stringify(form_est),
			contentType: "application/json; charset=utf-8",
			success: function(){
				$.ajax({
					type:'get',
					url: base_url + 'estado/all-estados/'+idEstablecimiento,
					success: function(data){
						console.log(data);
						$('#listaEst th').remove();
							$("#listaEst thead").append("<th style='display:none;'>Id</th><th>Estado</th><th><button type='button' id='addEst' class='btn btn-primary' data-toggle='modal' data-target='#modalNewEst'> <span class='glyphicon glyphicon-plus'></span> </button></th>");
							$('#listaEst tr').remove();
							$.each(data, function(i, item){
							$('<tr>').html(
								"<td  style='display:none;'>"+data[i].idEstado +"</td><td>"+data[i].descripcion+"</td>" +
								"<td><a href=#" +" target='blank' class='btn btn-danger'> <span "+"class='glyphicon glyphicon-remove'></span> </a></td></tr>").appendTo('#listaEst');
							});

							$('#modalNewEst').modal('hide');
							$('.modal-backdrop').remove();
					},
					error: function(){
						console.log('error al buscar estado');
					}
				});

				$('#modalNewEst').modal('hide');
				$('.modal-backdrop').remove();
			},
			error: function(){
				console.log('Error al crear estados');
			}
		});
		
	});

		$('#btnGuardaRaza').on('click',function(e){
			e.stopImmediatePropagation();
			var idEstablecimiento = $('#txtIdEstab').val();
	
			var form_raza = {
				'descripcion' : $('#txtNuevaRaza').val(),
				'idEstablecimiento' : idEstablecimiento
			}

			$.ajax({
				type:'post',
				url: base_url+'raza/save',
				data: JSON.stringify(form_raza),
				contentType: "application/json; charset=utf-8",
				success: function(){
					$.ajax({
						type:'get',
						url: base_url + 'raza/all-razas/'+idEstablecimiento,
						success: function(data){
							console.log(data);
							$('#listaRaza th').remove();
								$("#listaRaza thead").append("<th style='display:none;'>Id</th><th>Raza</th><th><button type='button' id='addRaza' class='btn btn-primary' data-toggle='modal' data-target='#modalNewRaza'> <span class='glyphicon glyphicon-plus'></span> </button></th>");
								$('#listaRaza tr').remove();
								$.each(data, function(i, item){
								$('<tr>').html(
									"<td  style='display:none;'>"+data[i].idRaza +"</td><td>"+data[i].descripcion+"</td>" +
									"<td><a href=#" +" target='blank' class='btn btn-danger'> <span "+"class='glyphicon glyphicon-remove'></span> </a></td></tr>").appendTo('#listaRaza');
								});

								$('#modalNewRaza').modal('hide');
								$('.modal-backdrop').remove();
						},
						error: function(){
							console.log('error al buscar razas');
						}
					});

					$('#modalNewRaza').modal('hide');
					$('.modal-backdrop').remove();

				},
				error: function(){
					console.log('Error al crear estados');
				}
			});
		
		});		

		$('#btneliminaCat').on('click',function(e){
			e.stopImmediatePropagation();
			var cat = $('#btneliminaCat').attr('data-id');
			alert(cat);
		});
});


