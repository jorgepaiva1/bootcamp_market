--ej1

Select nombre,apellido,count(*) from factura fact inner join cliente as cli on fact.cliente_id=cli.id 
group by nombre,apellido having count(*)>0 order by count(*) desc;


--ej2
--falta sumar las cantidades 

/*Select cliente.nombre, cliente.apellido, cliente.nro_cedula, sum(prod.precio * fact_det.cantidad) as gasto from 
( (cliente inner join factura as fact on cliente.id=fact.cliente_id) 
 inner join factura_detalle as fact_det on fact.id=fact_det.factura_id)
inner join producto as prod on fact_det.producto_id= prod.id
group by cliente.nombre,cliente.apellido,cliente.nro_cedula, (prod.precio * fact_det.cantidad)
order by gasto desc;*/


--ej3

select mon.nombre, count(*) AS Uso from factura fact inner join moneda mon on fact.moneda_id=mon.id
group by mon.nombre order by count(*) desc;


--ej4

Select prov.nombre, count(prov.id) as productos from producto as prod inner join proveedor as prov on prod.proveedor_id=prov.id
group by prov.nombre order by count(prov.id) desc; 


--ej5

Select prod.nombre,sum(prod.id*fact_det.cantidad) as cant_vendida from factura_detalle as fact_det inner join producto as prod
on fact_det.producto_id=prod.id group by prod.nombre order by sum(prod.id*fact_det.cantidad) desc;


--ej6

Select prod.nombre,sum(prod.id * fact_det.cantidad) as cant_vendida from factura_detalle as fact_det inner join producto as prod
on fact_det.producto_id=prod.id group by prod.nombre order by sum(prod.id*fact_det.cantidad) asc;


--ej7
--falta agregar tipo de factura
Select fact.fecha_emision,client.nombre,client.apellido,prod.nombre,fact_det.cantidad from 
((cliente as client inner join factura as fact on client.id=fact.cliente_id) 
 inner join factura_detalle as fact_det on fact.id=fact_det.factura_id)
 inner join producto as prod on fact_det.producto_id=prod.id
group by fact.fecha_emision,client.nombre,client.apellido,prod.nombre,fact_det.cantidad
order by fact.fecha_emision desc;


