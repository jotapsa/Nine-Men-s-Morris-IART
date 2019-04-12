module.exports.Cell = Object.freeze({
  BLOCKED: Symbol('blocked'),
  OPEN: Symbol('open'),
  RED: Symbol('red'),
  BLUE: Symbol('blue'),
});

module.exports.Turn = Object.freeze({
  RED: Symbol('red'),
  BLUE: Symbol('blue'),
});

module.exports.GameState = Object.freeze({
  PLACING: Symbol('placing'),
  MOVING: Symbol('moving'),
  GAME_OVER: Symbol('game_over'),
});

module.exports.MoveType = Object.freeze({
  PLACING: Symbol('placing'),
  MOVE_ORDINARY: Symbol('move'),
  MOVE_FREE: Symbol('move_free'),
});
