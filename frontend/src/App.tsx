import "./App.css";
import EventProvider from "./components/context/EventContext";
import Header from "./components/header/header";
import List from "./components/list/list";

function App() {
	return (
		<div className="app-container">
			<Header />

			<EventProvider>
				<List />
			</EventProvider>
		</div>
	);
}

export default App;
