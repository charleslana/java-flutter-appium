# Java+Appium

Requer Java 21

# Necessário driver do flutter para o appium

```bash
appium driver install --source=npm appium-flutter-driver
```

# Instalar dependências

Os testes serão executados em paralelo

```bash
mvn clean install
```

Os testes são pulados após instalação

```bash
mvn clean install -DskipTests
```

# Executar servidor do appium

```bash
appium --allow-cors --base-path /wd/hub
or
npx appium --allow-cors
```

# Executar todos os testes

```bash
mvn test
```

# Executar teste em grupo

```bash
mvn test -Dgroups="grupo"
```

# Executar teste pelo método da classe de teste

```bash
mvn test -Dtest=Classe#metodo
```

# Executar testes pelo testng.xml

```bash
mvn test -DsuiteXmlFile=testng.xml
```

# Listar emuladores

```bash
emulator -list-avds
```

# Relatórios

### Execução dos testes

