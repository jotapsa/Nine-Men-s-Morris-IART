const Cell = Object.freeze({
  BLOCKED: Symbol('blocked'),
  OPEN: Symbol('open'),
  Player0: Symbol('player0'),
  Player1: Symbol('player1'),
});

const GameState = Object.freeze({
  PLACING: Symbol('placing'),
  MOVING: Symbol('moving'),
  GAME_OVER: Symbol('game_over'),
});

const MoveType = Object.freeze({
  PLACING: Symbol('placing'),
  MOVE_ORDINARY: Symbol('move'),
  MOVE_FREE: Symbol('move_free'),
});

module.exports = {
  Cell,
  GameState,
  MoveType,
};
