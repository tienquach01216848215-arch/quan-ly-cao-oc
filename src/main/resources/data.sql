INSERT INTO nguoi_dung (ten_dang_nhap, mat_khau, ho_ten, email, vai_tro) 
VALUES ('admin', '123456', 'Administrator', 'admin@gmail.com', 'ADMIN') 
ON CONFLICT DO NOTHING;
