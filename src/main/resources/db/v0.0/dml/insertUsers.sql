INSERT INTO users (username, password, role) VALUES
('admin', crypt('admin123', gen_salt('bf')), 'ADMIN'),
('journalist1', crypt('journalist1', gen_salt('bf')), 'JOURNALIST'),
('journalist2', crypt('journalist2', gen_salt('bf')), 'JOURNALIST'),
('subscriber1', crypt('subscriber1', gen_salt('bf')), 'SUBSCRIBER'),
('subscriber2', crypt('subscriber2', gen_salt('bf')), 'SUBSCRIBER'),
('subscriber3', crypt('subscriber3', gen_salt('bf')), 'SUBSCRIBER'),
('subscriber4', crypt('subscriber4', gen_salt('bf')), 'SUBSCRIBER'),
('subscriber5', crypt('subscriber5', gen_salt('bf')), 'SUBSCRIBER'),
('subscriber6', crypt('subscriber6', gen_salt('bf')), 'SUBSCRIBER'),
('subscriber7', crypt('subscriber7', gen_salt('bf')), 'SUBSCRIBER'),
('subscriber8', crypt('subscriber8', gen_salt('bf')), 'SUBSCRIBER'),
('subscriber9', crypt('subscriber9', gen_salt('bf')), 'SUBSCRIBER');