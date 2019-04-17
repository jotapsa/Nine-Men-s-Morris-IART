// eslint-disable-next-line max-len

const GameState = require('../logic/gameState');
const Move = require('../logic/move');


module.exports = class GameController {
  constructor(state) {
    this.state = state;
    this.currentMove = null;

    this.waitingForEnd = false;
    this.waitingForTaken = false;
  }

  input(input) {
    console.log(`Input - ${input}`);

    const moveInput = Number.parseInt(input, 10);
    if (moveInput < 0 || moveInput >= 24) {
      return;
    }

    if (!(this.state instanceof GameState)) {
      return;
    }

    if (this.currentMove === null) {
      this.currentMove = Move(input);
      if (this.state.isInPlacingPhase()) {
        if (this.state.checkIfMoveCausesMill(this.currentMove)) {
          this.waitingForTaken = true;
          return;
        }
      } else {
        this.waitingForEnd = true;
        return;
      }
    } else if (this.waitingForEnd) {
      this.currentMove.end = moveInput;
      if (this.state.checkIfMoveCausesMill(this.currentMove)) {
        this.waitingForTaken = true;
        return;
      }
    } else if (this.waitingForTaken) {
      this.currentMove.taken = moveInput;
    }

    if (this.state.isValidMove(this.currentMove)) {
      this.state.executeMove(this.currentMove);
    }

    this.currentMove = null;
  }
};
