INSERT INTO NguoiDung (TenDangNhap, MatKhau, HoTen, Email, VaiTro) 
VALUES ('admin', '123456', 'Administrator', 'admin@gmail.com', 'ADMIN')
ON CONFLICT DO NOTHING;
