// eslint-disable-next-line max-len
// TODO: when using a browser we need to use this layer to transform the event into something usable by the logic

module.exports = class GameController {
  constructor(model) {
    this.model = model;
  }

  input(move) {
    console.log(`Received move ${move}`);
  }
};
