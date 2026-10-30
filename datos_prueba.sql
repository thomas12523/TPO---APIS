-- Datos de prueba para la base marketplace (GymStore)
-- Correr con el back levantado al menos una vez (para que Hibernate cree las tablas)
-- y con los usuarios ya registrados desde el front o por /Auth/register:
--   admin / admin123  y  thom0408
-- Los IDs se buscan por nombre, así que no importa qué IDs tenga cada uno.

USE marketplace;

-- 1. El usuario admin pasa a ser administrador (después hay que volver a iniciar sesión)
UPDATE usuario SET role = 'ADMIN' WHERE username = 'admin';

-- 2. Categorías
INSERT INTO category (nombre, activo) VALUES
('Pesas', 1),
('Máquinas', 1),
('Indumentaria', 1),
('Suplementos', 1),
('Accesorios', 1);

-- 3. Productos (stock variado: uno sin stock y algunos con poco, para probar los avisos)
INSERT INTO producto (categoria_id, nombre_producto, descripcion, precio_unitario, stock, activo) VALUES
((SELECT id FROM category WHERE nombre = 'Pesas'), 'Mancuernas ajustables Pro', 'Set de mancuernas de 5 a 40 kg con rack incluido.', 185000, 12, 1),
((SELECT id FROM category WHERE nombre = 'Pesas'), 'Barra olímpica 20 kg', 'Barra de competición de 28 mm con moleteado de precisión.', 140000, 20, 1),
((SELECT id FROM category WHERE nombre = 'Pesas'), 'Set de discos bumper 120 kg', 'Discos de uretano calibrados con tolerancia de 10 gramos.', 520000, 7, 1),
((SELECT id FROM category WHERE nombre = 'Pesas'), 'Barra hexagonal', 'Barra trap para peso muerto con doble agarre elevado.', 165000, 3, 1),
((SELECT id FROM category WHERE nombre = 'Máquinas'), 'Jaula de potencia X1', 'Rack multifuncional de acero con barra de dominadas.', 980000, 4, 1),
((SELECT id FROM category WHERE nombre = 'Máquinas'), 'Banco multiángulo', 'Banco regulable de 7 posiciones, soporta hasta 450 kg.', 230000, 0, 1),
((SELECT id FROM category WHERE nombre = 'Máquinas'), 'Bicicleta de spinning', 'Rueda de inercia de 20 kg y resistencia magnética.', 610000, 5, 1),
((SELECT id FROM category WHERE nombre = 'Indumentaria'), 'Remera técnica Dry', 'Remera de entrenamiento que seca rápido. Talle único.', 28000, 40, 1),
((SELECT id FROM category WHERE nombre = 'Indumentaria'), 'Guantes de entrenamiento', 'Guantes con muñequera y palma acolchada.', 19500, 25, 1),
((SELECT id FROM category WHERE nombre = 'Suplementos'), 'Proteína whey 1 kg', 'Proteína de suero sabor vainilla, 33 porciones.', 42000, 30, 1),
((SELECT id FROM category WHERE nombre = 'Suplementos'), 'Creatina monohidrato 300 g', 'Creatina micronizada sin sabor.', 31000, 18, 1),
((SELECT id FROM category WHERE nombre = 'Accesorios'), 'Kettlebell de hierro 16 kg', 'Pesa rusa de hierro fundido con base plana.', 64000, 9, 1),
((SELECT id FROM category WHERE nombre = 'Accesorios'), 'Soga de saltar con rulemanes', 'Soga de acero regulable con mangos de aluminio.', 15000, 50, 1);

-- 4. Imágenes: ya no se cargan por URL. Se suben desde el panel admin (Productos > editar)

-- 5. Descuentos: dos vigentes (se ven como "Oferta") y uno desactivado
INSERT INTO descuento (producto_id, porcentaje, fecha_inicio, fecha_fin, activo) VALUES
((SELECT producto_id FROM producto WHERE nombre_producto = 'Barra olímpica 20 kg'), 15, '2026-10-01', '2026-12-31', 1),
((SELECT producto_id FROM producto WHERE nombre_producto = 'Proteína whey 1 kg'), 20, '2026-10-01', '2026-12-31', 1),
((SELECT producto_id FROM producto WHERE nombre_producto = 'Kettlebell de hierro 16 kg'), 10, '2026-10-01', '2026-12-31', 0);

-- 6. Reseñas del usuario admin (thom0408 queda libre para probar escribir una)
INSERT INTO resena (producto_id, usuario_id, puntuacion, comentario, fecha, activo) VALUES
((SELECT producto_id FROM producto WHERE nombre_producto = 'Jaula de potencia X1'), (SELECT usuario_id FROM usuario WHERE username = 'admin'), 5, 'Muy sólida, no se mueve nada aunque no esté amurada.', '2026-10-05', 1),
((SELECT producto_id FROM producto WHERE nombre_producto = 'Mancuernas ajustables Pro'), (SELECT usuario_id FROM usuario WHERE username = 'admin'), 4, 'Excelente calidad, el cambio de peso es rápido.', '2026-10-08', 1);
