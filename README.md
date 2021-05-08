# Mafia
Mafia is a popular party game that requires one to think, manipulate, and deceive others in
order to win.\
Since the pandemic, it has become rather difficult for people to host parties; hence
for this application, I developed a mafia game simulator.\
In  this application ,I used OOP concepts, especially inheritance and polymorphism, demonstration of object
comparison,and object equality check.


<p align="center">
  <img src="https://github.com/ashcode028/Mafia/blob/89ce38530f345ac8ee612669ba1d1893f54c2bbb/logo.jpg"/>
</p>

## Rules of Game:
### Plot
  - There is a village of __N players__. 
  - A player can be either a commoner, a detective, a healer,
or a mafia. 
  - A commoner only knows that he is a commoner. A detective knows all the other
detectives. 
  - A mafia knows all the other Mafia players. A healer knows all other healers in the
game.
### Objectives: 
- The objective for the mafias is to kill or eliminate all the non-mafias such that the
ratio of the alive mafias to all others is 1:1. 
- A player can be eliminated in two ways:
  1) By being killed by the Mafia or 
  2) Be eliminated in a vote out. Once a player is eliminated, they cannot be brought back to life. 
  3) Mafias cannot kill themselves.
- The objective for all other players(except the mafias) is to eliminate the mafias through a vote
out(as the Mafias cannot be killed). Therefore, by using special powers of detectives and
healers, they are required to save themselves and vote out the Mafias.
### End of Game:
The game ends when either all mafias are voted out or the ratio of mafias to others becomes
1:1.\
The Mafia wins in the latter case and loses in the former.\
You need to announce the winners at the end of the game along with the roles of each player.
## Design of the game
__[A] On starting the game:__
  1) Each invocation of the game is considered as a new game. Hence, no need to create a
database of users or saving their game.\
At the beginning of each execution, the user is supposed to enter the number N, i.e., the total number of players in the game.\
The minimum number of players for the game is 6. If the player enters a number less than 6,
the program asks for N again.\
__Under no circumstances the program terminates unexpectedly.__
If any invalid input is given, it is handled properly.
  2) Then the user is given the option of either choosing their character (Mafia, Commoner,
Detective, or Healer) on their own or be allotted a character randomly by the program.\
After allocating the user his/her role, there should be N/5 Mafias, N/5 Detectives, max {1,
N/10} healers, and the remaining players should be commoners.\
Here, the ‘/’ operator is floored division. For example, for N=11, there will be 2 mafias,2 detectives, 1 healer, and 6 commoners.\
__[B] Gameplay__
- The game will run in rounds. In each round, there will be fixed steps that are followed.
  - Firstly the Mafias choose to kill a person(other than mafias). If the user is a mafia
player, then he/she will be asked to choose the target.
  - Then the detective will test a person to know whether he is mafia or not. If the
user is a detective player, then he/she will be asked who they want to test.
  - Then the healer will heal a person by giving him an HP boost as defined in HP
rules. If the user is a healer player, then he/she will be asked who they want to
heal.
  - If after healing, the HP of the person killed by the Mafia reaches 0, the player
dies.
  - After this, there is a voting round where each player votes randomly, and the
user(If alive) votes for whoever he/she wants to vote for. The person with the
highest votes is kicked out of the game.
  - However, if the detective tests positive on a mafia, there will be no voting, and
the caught mafia will be voted out by default. Once a player is out of the game,
they can not re-enter the game.
  - If there are no detectives left, there will be no test and/or if there are no healers
left, there will be no HP boost. The game will keep on moving until we arrive at an
end of game condition.
  - If there are multiple mafias then they vote collectively i.e. they decide one player
to kill. In the same way, all detectives choose one player to test and healers
choose one player to heal. At no point in time mafias can kill multiple players in a
single round, or detectives can test multiple players, and/or healers can heal
multiple players. Although when taking the vote, each player votes independently
so as to protect his/her identity.
  - In each round, there is fresh voting that takes place. Votes from previous rounds
are not counted. Only one player is voted out in each round
  - In case the votes are tied, the voting process is repeated until there is no
tie.
- HP Rules
  - All commoners start with an HP of 1000 each.
  - All mafias will start with 2500 HP each.
  - All Detectives and Healers start with an HP of 800 each.
  - In each Round, Healers can increase any players HP by 500.
  - When the mafias choose a target, if their combined HP is equal to or more than
that target's current HP, the target's HP becomes 0. However, if their combined
HP is less than that targets current HP, the targets HP reduces by the combined
HP of the mafias.
  - Further, as damage, each mafias HP will be reduced by X/Y, where X is the initial
HP of the target(before being killed), and Y is the number of alive mafias whose
HP is greater than 0. If the target’s HP falls below or equal to 0, the target dies.
For example, if there are 2 mafias with HP 500 and 1000. Suppose the target has
an HP of 1000. Then, since the combined HP of mafias (1500) is greater than the
target's HP, the target dies. And the new HP of the mafias is 0 and 500,
respectively.\
__Note that mafias cannot be killed even if their HP falls to 0. They can only
be voted out. Please note that HP can not fall below 0 for any player.__<br/>
In case, at the time of selection of target, one of the mafias has an HP less than
X/Y, then this mafia will contribute to the damage by his total HP(making his HP
to zero), and the remaining Damage will be equally divided. This process is
continued until all Damage is absorbed, or the HP of all mafias becomes 0. For
example, if there are two mafias mafia1 and mafia2 with HP 100 and 200
respectively and they kill a player with HP 250, then the final HP of mafias after
the kill are 0 and 50.<br/>
  - If the Healer chooses a person who was a target that died in that round itself, the
target is revived, and the new HP of the target is 500. No one dies in such a
situation. However, the mafias will still take the damage as explained earlier.
In other words, the target is chosen first, and then the mafias and target take their
damages, after which the healer chooses to heal someone. However, the target
dies if his HP becomes zero and is not chosen by the Healer to heal.
  - The healer can choose any player(including mafia) for the HP boost that is
currently playing the game. Players that were killed/voted out in the previous
rounds don’t participate in any way and hence can’t be chosen in any scenario.
- There is only a single-player playing the game. Other players are simulated and need no
input.
- The output should give the details of the game. Several samples of output are shown
below.
