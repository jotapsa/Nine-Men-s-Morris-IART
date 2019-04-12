const readline = require('readline');
const Game = require('../logic/game.js');

const prompt = readline.createInterface(
  process.stdin,
  process.stdout,
);

const myGame = new Game();

console.log(myGame);
prompt.question("What's your move? ", (move) => {
  console.log(`Received: ${move}`);
});
