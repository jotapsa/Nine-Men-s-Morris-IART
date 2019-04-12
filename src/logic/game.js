const Cell = Object.freeze({
  BLOCKED: Symbol('blocked'),
  OPEN: Symbol('open'),
  RED: Symbol('red'),
  BLUE: Symbol('blue'),
});

const Turn = Object.freeze({
  RED: Symbol('red'),
  BLUE: Symbol('blue'),
});

const GameState = Object.freeze({
  PLACING: Symbol('placing'),
  MOVING: Symbol('moving'),
  GAME_OVER: Symbol('game_over'),
});

module.exports = class Game {
  constructor() {
    // eslint-disable-next-line max-len
    this.board = [
      [Cell.OPEN, Cell.BLOCKED, Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED, Cell.BLOCKED, Cell.OPEN],
      [Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED],
      [Cell.BLOCKED, Cell.BLOCKED, Cell.OPEN, Cell.OPEN, Cell.OPEN, Cell.BLOCKED, Cell.BLOCKED],
      [Cell.OPEN, Cell.OPEN, Cell.OPEN, Cell.BLOCKED, Cell.OPEN, Cell.OPEN, Cell.OPEN],
      [Cell.BLOCKED, Cell.BLOCKED, Cell.OPEN, Cell.OPEN, Cell.OPEN, Cell.BLOCKED, Cell.BLOCKED],
      [Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED],
      [Cell.OPEN, Cell.BLOCKED, Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED, Cell.BLOCKED, Cell.OPEN],
    ];

    this.turn = Turn.RED;

    this.gameState = GameState.PLACING;
  }
};
