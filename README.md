# Playlist Maker

Финальный проект курса Android-разработчик.

## Спринты

### Sprint 3 (v3.0)

**Задача 1. Главный экран**

- `MainActivity`, объявление в `AndroidManifest.xml`
- Главный экран на Jetpack Compose по макету Figma
- Toast «Нажата кнопка "…"» при нажатии на пункты меню (на этапе задачи 1 — для всех кнопок)

**Задача 2. Переходы между экранами**

- `SearchActivity` и `SettingsActivity` в манифесте
- Переходы на экраны поиска и настроек по кнопкам «Поиск» и «Настройки» (без Toast)
- Toast остаётся для «Плейлисты» и «Избранное» (экраны появятся в следующих спринтах)

### Sprint 4 (v4.0)

**Задача 1. Вёрстка экрана поиска**

- Вёрстка `SearchScreen` на Jetpack Compose по макету Figma
- Placeholder «Поиск», если запрос пустой
- Иконка лупы в начале поля ввода (логика поиска не реализована)
- Иконка «×» в конце поля — сброс поискового запроса

**Задача 2. Вёрстка экрана настроек**

- Вёрстка экрана настроек по макету Figma
- «Поделиться приложением» — диалог для шеринга
- «Написать разработчикам» — почтовый клиент с предзаполненными полями
- «Пользовательское соглашение» — оферта Яндекс Практикума в браузере
- Названия кнопок и тексты письма в `strings.xml`, в Intent — `stringResource()`

**Задача 3. Переходы между экранами**

- Enum-класс `PlaylistScreen`
- `PlaylistHost` с `NavHost` (`MainScreen`, `SearchScreen`, `SettingsScreen`)
- Переходы на экраны поиска и настроек по кнопкам главного меню

### Sprint 5 (v5.0)

**Шаг 1. Репозиторий**

- `TracksRepository` с методом `getAllTracks()`
- `TracksRepositoryImpl` — имитация запроса (`delay(1000)`) и список `listTracks`
- `TrackSearchInteractor` и `TrackSearchInteractorImpl`

**Шаг 2. View Model**

- `SearchState` (`Initial`, `Loading`, `Success`, `Error`)
- `SearchViewModel` с `allTracksScreenState` и `fetchData()`

**Шаг 3. UI**

- `SearchTrackListItem` с иконкой `ic_music`
- `AllTracksScreen` — `LazyColumn`, лоадер и обработка ошибок
- Список треков на экране поиска (`SearchScreen`)

### Sprint 6 (v6.0)

**SearchViewModel и состояния поиска**

- `SearchState`: `Initial`, `Searching`, `Success(list)`, `Fail(error)`
- `SearchViewModel` с `searchScreenState` и методом `search(whatSearch: String)`
- `Creator.getTracksRepository()` → `TracksRepositoryImpl(RetrofitNetworkClient(Storage()))`

**SearchScreen**

- Поиск по нажатию на иконку лупы и по клавише Search на клавиатуре
- Отображение состояний: подсказка, лоадер, список результатов, ошибка
- `SearchTrackListItem` с иконкой `ic_music`

## Ветки

| Спринт   | Ветка      | Тег   |
|----------|------------|-------|
| Sprint 3 | `sprint-3` | v3.0  |
| Sprint 4 | `sprint-4` | v4.0  |
| Sprint 5 | `sprint-5` | v5.0  |
| Sprint 6 | `sprint-6` | v6.0  |

## Самопроверка

Чек-листы лежат в папке [`checklists/`](checklists/).

## Update: track covers by URL

Track covers are now connected through `artworkUrl` in `MockTracks.kt`.
The app uses Coil `AsyncImage`, so covers load from the internet.
The project already has `INTERNET` permission in `AndroidManifest.xml`.
