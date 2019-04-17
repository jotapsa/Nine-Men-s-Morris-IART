// eslint-disable-next-line max-len

const GameState = require('../logic/gameState');
const Move = require('../logic/move');


module.exports = class GameController {
  constructor(state) {
    this.state = state;
    this.currentMove = null;
  }

  input(input) {
    console.log(`Input - ${input}`);

    const moveInput = Number.parseInt(input, 10);
    if (moveInput < 0 || moveInput >= 24) {
      return;
    }

    if (this.currentMove === null) {
      this.currentMove = Move(input);
    }
  }
  // if (this.currentMove === null) {
  //   this.currentMove = Move(moveInput);

  //   //move.start = moveInput
  //   if (/*state in placing phase*/) {
  //     if (/*state.moveCausesMill(move)*/) {
  //       //setWaitingForTaken
  //       return; //pq precisamos da peça
  //     } else {
  //       //return move ou break?
  //     }
  //   } else if (/*state in moving phase*/) {
  //     //setWaitingforTo
  //     return; //pq precisamos do move.to
  //   }
  // } else if (/*WaitingForTo*/) {
  //   //move.to = moveInput
  //   if (/*state.moveCausesMill(move)*/) {
  //     //setWaitingforTaken
  //     return; //pq precisamos da peça
  //   } else {
  //     //return move ou break;
  //   }
  // } else if (/*WaitingForTaken*/) {
  //   //move.taken = moveInput;
  //   //return move ou break
  // }
  // do while !State.isValidmove(move))
};
