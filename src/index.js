const readline = require('readline');

const Game = require('./logic/game.js');
const GameController = require('./controller/game-controller.js');
const GameView = require('./view/cli.js').cliView;
const { Cell } = require('./utils/game_utils.js');
const Player = require('./logic/player.js');
const Computer = require('./logic/computer.js');

const { GameState } = require('./utils/game_utils.js');

const r1 = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
  prompt: '> ',
});

const player0 = new Player(Cell.Player0);
const player1 = new Player(Cell.Player1);
const game = new Game(player0, player1);
const gameController = new GameController(game);

// console.log('\x1Bc');
GameView.render(game);
console.log("What's your move (e.g.: D3) ? ");
r1.prompt();
r1.on('line', (input) => {
  gameController.input(input.trim());
  // console.log('\x1Bc');
  GameView.render(game);
  console.log("What's your move (e.g.: D3) ? ");
  r1.prompt();
});

r1.on('close', () => {
  console.log('Come again!');
  process.exit(0);
});
