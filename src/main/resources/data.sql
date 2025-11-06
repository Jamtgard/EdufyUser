-- ED-259-SA
INSERT INTO edufy_user (id, user_sub, user_username, user_email, user_creator, user_active) VALUES
(1,'00000000-0000-0000-0001-000000000001', 'Sunstar', 'emil.beckstom@gmail.com',false, true),
(2, '00000000-0000-0000-0000-000000000002', 'Neon Pulse', 'mia.nickleson@gmail.com', true, true),
(3, '00000000-0000-0000-0003-000000000003', 'WillStorm', 'william.strom@gmail.com', false, false),
(4, '00000000-0000-0000-0004-000000000004', 'Woodsburo', 'sydney.prescott@gmail.com', false, true),
(5, '00000000-0000-0000-0000-000000000005', 'Pixel Muse', 'mona.lisa@gmail.com', true, true),
(6, '00000000-0000-0000-0006-000000000006', 'Musse', 'micke.ericson@gmail.com', false, true),
(7, '00000000-0000-0000-0007-000000000007', 'Mollweee', 'molle.nordstrom@gmail.com', false, true),
(8, '00000000-0000-0000-0008-000000000008', 'KalleA', 'kalle.anka@gmail.com', false, true),
(9, '00000000-0000-0000-0009-000000000009', 'MimiP', 'mimi.pid@gmail.com', false, false),
(10, '00000000-0000-0000-0010-000000000010', 'DustKiller', 'anna.willson@gmail.com', false, true);

-- ED-89-AA (History)
-- Song history
INSERT INTO song_history_user (user_id, song_history_id) VALUES
                                                             (1, 101), (1, 102), (1, 103),
                                                             (2, 201), (2, 202);
-- Album history
INSERT INTO album_history_user (user_id, album_history_id) VALUES
                                                               (1, 301), (1, 302),
                                                               (2, 303);
-- Video clip history
INSERT INTO video_clip_history_user (user_id, video_clip_history_id) VALUES
                                                                         (1, 401), (1, 402),
                                                                         (2, 403), (2, 404);
-- Video playlist history
INSERT INTO video_playlist_history_user (user_id, video_playlist_history_id) VALUES
                                                                                 (1, 501),
                                                                                 (2, 502), (2, 503);
-- Pod episode history
INSERT INTO pod_episode_history_user (user_id, pod_episode_history_id) VALUES
                                                                           (1, 601), (1, 602),
                                                                           (2, 603);
-- Pod season history
INSERT INTO pod_season_history_user (user_id, pod_season_history_id) VALUES
                                                                         (1, 701),
                                                                         (2, 702), (2, 703);