const readline = require('readline');

const Game = require('./logic/game.js');
const GameController = require('./controller/game-controller.js');
const GameView = require('./view/cli.js').cliView;

const { GameState } = require('./utils/game_utils.js');

const r1 = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
  prompt: '> ',
});

const game = new Game();
const gameController = new GameController(game);

// console.log('\x1Bc');
GameView.render(game);
console.log("What's your move (e.g.: 3D) ? ");
r1.prompt();
r1.on('line', (input) => {
  gameController.input(input.trim());
  // console.log('\x1Bc');
  GameView.render(game);
  console.log("What's your move (e.g.: 3D) ? ");
  r1.prompt();
});

r1.on('close', () => {
  console.log('Come again!');
  process.exit(0);
});
