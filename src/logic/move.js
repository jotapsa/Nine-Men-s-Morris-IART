module.exports = class Move {
  constructor(moveType, endPos, startPos) {
    this.moveType = moveType;
    this.endPos = endPos;
    this.startPos = startPos;
  }
};
