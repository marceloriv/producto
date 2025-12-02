-- Insert default Inventory
INSERT INTO inventario (fecha_actualizacion) VALUES (CURRENT_DATE);

-- Insert Products
INSERT INTO producto (nombre, categoria, precio, descripcion, image, id_inventario, stock) VALUES
('Proteina Whey', 'Proteínas', 29990, 'Proteína aislada de suero 1kg', 'http://app-react-powerfit.s3-website-us-east-1.amazonaws.com/images/Proteina.png', 1, 100),
('Creatina Monohidrato', 'Recuperación', 19990, 'Monohidrato puro 300g', 'http://app-react-powerfit.s3-website-us-east-1.amazonaws.com/images/creatina.png', 1, 100),
('BCAA', 'Aminoácidos', 15550, 'Aminoácidos ramificados 200g', 'http://app-react-powerfit.s3-website-us-east-1.amazonaws.com/images/bcaa.png', 1, 100),
('Pre-Entreno', 'Pre-Entreno', 24000, 'Mezcla pre-entreno 250g', 'http://app-react-powerfit.s3-website-us-east-1.amazonaws.com/images/pre-entreno.png', 1, 100),
('Multivitamínico', 'Vitaminas', 12500, 'Complejo multivitamínico diario 60 cápsulas', 'http://app-react-powerfit.s3-website-us-east-1.amazonaws.com/images/multivitaminico.png', 1, 100),
('Vitamina D3', 'Vitaminas', 9990, 'Vitamina D3 2000 IU, 90 cápsulas', 'http://app-react-powerfit.s3-website-us-east-1.amazonaws.com/images/vitamina-d.png', 1, 100),
('Omega-3', 'Salud', 18000, 'Aceite de pescado omega-3 1000mg, 60 cápsulas', 'http://app-react-powerfit.s3-website-us-east-1.amazonaws.com/images/omega-3.png', 1, 100),
('Magnesio', 'Recuperación', 16500, 'Magnesio para recuperación', 'http://app-react-powerfit.s3-website-us-east-1.amazonaws.com/images/magnesio.png', 1, 100),
('Glutamina', 'Recuperación', 22000, 'L-Glutamina 500g para recuperación muscular', 'http://app-react-powerfit.s3-website-us-east-1.amazonaws.com/images/glutamina.png', 1, 100),
('Vitamina C', 'Vitaminas', 8500, 'Vitamina C 1000mg, 100 comprimidos', 'http://app-react-powerfit.s3-website-us-east-1.amazonaws.com/images/vitamina C.png', 1, 100),
('Probióticos', 'Salud', 21000, 'Mezcla probiótica para salud digestiva, 60 cápsulas', 'http://app-react-powerfit.s3-website-us-east-1.amazonaws.com/images/probioticos.webp', 1, 100),
('Complejo B', 'Vitaminas', 11500, 'Vitamina B complex para energía y metabolismo, 60 comprimidos', 'http://app-react-powerfit.s3-website-us-east-1.amazonaws.com/images/complejo b.jpg', 1, 100);
