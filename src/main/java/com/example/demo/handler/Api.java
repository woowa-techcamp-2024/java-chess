package com.example.demo.handler;

import com.example.demo.context.Game;
import com.example.demo.context.Location;
import com.example.demo.context.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Api {

    Map<String, Game> session = new HashMap<>();

    /**
     * 새로운 게임을 시작합니다.
     * @param blackUser 참여할 유저의 정보1
     * @param whiteUser 참여할 유저의 정보2
     * @return 새로운 게임의 id
     */
    public String start(User blackUser, User whiteUser) {
        Game game = Game.builder()
                .blackUser(blackUser)
                .whiteUser(whiteUser)
                .build();

        game.initBoard();
        game.start();

        var id = UUID.randomUUID().toString();
        session.put(id, game);
        return id;
    }

    public void move(String id, String from, String to) {
        var game = session.get(id);
        game.handleMoveCommand(Location.of(from), Location.of(to));
    }

    /**
     * 기물이 이동 가능한 경로를 반환합니다.
     * @param id 적용할 게임의 id
     * @param target 기물의 위치
     * @return 지정한 기물이 이동 가능한 위치 목록
     */
    public List<Location> getLocations(String id, String target) {
        var game = session.get(id);
        return game.candidateLocations(Location.of(target));
    }

    /**
     * 게임을 종료합니다.
     * @param id 종료할 게임의 id
     */
    public void end(String id) {
        var game = session.get(id);
        game.end();
    }
}
