# PomokuCzaszkiKm
Plugin stworozny na wersję 1.19.2, opartą na silniku Spigot jest ulepszoną kopią pluginu z Kwadratowej Masakry. Plugin ma wbudowane API, z którego w przyszłości będzie można korzystać. Do tego system ten dodaje kary i nagrady za zabijanie graczy oraz segregowanie graczy na pokojowych i agresywnych.
Plugin do poprawnego działania potrzebuje pluginu [Vault](https://github.com/MilkBowl/VaultAPI), ponieważ wykorzystuje jego system ekonomi do dawania kar i nagród.

### Działanie
Po dołączeniupierwszy raz na serwer gracz otrzymuje range gracza pokojowego. Gdy zdecyduje się zabić jednak innego gracza jest karany w wysokości kary, którą możę ustawić właściciel serwera i tz. czaszką. Gdy gracz dostanie czaszkę, koło jego nick'u pojawia się ikona czaszki, która informuje pozostałych graczy o tym, że gracz jest agresywny. Gdy osoba o agresywna zostanie zabita, zamienia się w osobe pokojową, a gracz który ją zabił dostaje nagrode, która ustala właściciel serwera w pliku config.yml. Po zabicu gracza agreywnego i jego przemianie w gracza pokojowego czaszka przy jego nicku jest usuwana.
