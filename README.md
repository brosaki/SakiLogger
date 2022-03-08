# SakiLogger
Logs system using Discord Bot and .txt file.

#Settings.yml:
```yml
discord-bot:
  toggle: false
  token: ""
  discord-logs-channel: ""

logs-file:
  toggle: true

logs-settings:
  log-bypass: false #if true, every players with 'sakilogger.bypass' will be ignored
  time-format: 'yyyy-MM-dd HH:mm:ss'

#Player Loggers

# Global Placeholders:
# {player_name}
# {player_coords}
# {date_time}
# {server_ip}
# {server_name}

player-join:
  toggle: true
  file: "[JOIN] {player_name} joined at {date_time}"
  embed:
    title: '**Player Join Event!**'
    field:
      text: '**A player appears!**'
      value: 'Nick: {player_name} %nl% Coords: {player_coords} %nl% Date: {date_time}'
    footer:
      text: 'SakiLogger is awesome <3'
      image: 'https://i.imgur.com/Ynvx0Sd.png'

player-quit:
  toggle: true
  file: "[QUIT] {player_name} leaved at {date_time}"
  embed:
    title: '**Player Quit Event!**'
    field:
      text: '**A player leave!**'
      value: 'Nick: {player_name} %nl% Coords: {player_coords} %nl% Date: {date_time}'
    footer:
      text: 'SakiLogger is awesome <3'
      image: 'https://i.imgur.com/Ynvx0Sd.png'

player-gamemode:
  # [+] Placeholder: {old_gamemode} {new_gamemode}
  toggle: true
  file: "[GAMEMODE] {player_name} changed the {old_gamemode} to {new_gamemode}"
  embed:
    title: '**Player Gamemode Event!**'
    field:
      text: '**A player changed their gamemode!**'
      value: 'Nick: {player_name} %nl% **Gamemode**: {old_gamemode} TO {new_gamemode} %nl% Coords: {player_coords} %nl% Date: {date_time}'
    footer:
      text: 'SakiLogger is awesome <3'
      image: 'https://i.imgur.com/Ynvx0Sd.png'

player-chat:
  # [+] Placeholder: {message}
  toggle: true
  file: "[CHAT] {player_name} | {message} | {date_time}"
  embed:
    title: '**Player Chat Event!**'
    field:
      text: '**A player send something in chat**'
      value: 'Nick: {player_name} %nl% Coords: {player_coords} %nl% Message: {message} %nl%Date: {date_time}'
    footer:
      text: 'SakiLogger is awesome <3'
      image: 'https://i.imgur.com/Ynvx0Sd.png'

player-command:
  # [+] Placeholder: {command}
  toggle: true
  unregistered-commands:
    - '/login'
    - '/report'
  file: "[COMMAND] {player_name} | {command} | {date_time}"
  embed:
    title: '**Player Command Event!**'
    field:
      text: '**A player send a command**'
      value: 'Nick: {player_name} %nl% Coords: {player_coords} %nl% Command: {command} %nl%Date: {date_time}'
    footer:
      text: 'SakiLogger is awesome <3'
      image: 'https://i.imgur.com/Ynvx0Sd.png'

player-block-place:
  # [+] Placeholder: {block_material} {block_coords}
  toggle: true
  registered-blocks:
    - BEDROCK
    - OBSIDIAN
  file: "[BLOCK_PLACE] {player_name} | {block_material} | {block_coords} | {date_time}"
  embed:
    title: '**Block Place Event!**'
    field:
      text: '**A player place a block!**'
      value: 'Nick: {player_name} %nl% Coords: {player_coords} %nl% Block Type: {block_material} %nl% Block Coords: {block_coords} %nl%Date: {date_time}'
    footer:
      text: 'SakiLogger is awesome <3'
      image: 'https://i.imgur.com/Ynvx0Sd.png'

player-block-break:
  # [+] Placeholder: {block_material} {block_coords}
  toggle: true
  registered-blocks:
    - BEDROCK
    - CHEST
    - ENDER_CHEST
  file: "[BLOCK_BREAK] {player_name} | {block_material} | {block_coords} | {date_time}"
  embed:
    title: '**Block Break Event!**'
    field:
      text: '**A player break a block!**'
      value: 'Nick: {player_name} %nl% Coords: {player_coords} %nl% Block Type: {block_material} %nl% Block Coords: {block_coords} %nl%Date: {date_time}'
    footer:
      text: 'SakiLogger is awesome <3'
      image: 'https://i.imgur.com/Ynvx0Sd.png'
```
 
# Events
 
<h4> Player Join Event
<h4> Player Quit Event
<h4> Player Chat Event
<h4> Player Command Event 
<h4> Player Gamemode Event
<h4> Block Place Event
<h4> Block Break Event

                          
