import axios, { AxiosRequestConfig } from "axios";

export const apiInstance = axios.create({
	baseURL: import.meta.env.VITE_BASE_URL,
});

export const apiPath = {
	events: "/events",
};

export const api = {
	get: <T>(url: string, config?: AxiosRequestConfig) =>
		new Promise<T>((resolve, reject) => {
			apiInstance
				.get(url, config)
				.then((response) => resolve(response.data))
				.catch((error) => reject(error));
		}),
	post: <T>(url: string, data?: unknown, config?: AxiosRequestConfig) =>
		new Promise<T>((resolve, reject) => {
			apiInstance
				.post(url, data, config)
				.then((response) => resolve(response.data))
				.catch((error) => reject(error));
		}),
	put: <T>(url: string, data?: unknown, config?: AxiosRequestConfig) =>
		new Promise<T>((resolve, reject) => {
			apiInstance
				.put(url, data, config)
				.then((response) => resolve(response.data))
				.catch((error) => reject(error));
		}),
	delete: <T>(url: string, config?: AxiosRequestConfig) =>
		new Promise<T>((resolve, reject) => {
			apiInstance
				.delete(url, config)
				.then((response) => resolve(response.data))
				.catch((error) => reject(error));
		}),
	patch: <T>(url: string, data?: T, config?: AxiosRequestConfig) =>
		new Promise<T>((resolve, reject) => {
			apiInstance
				.patch(url, data, config)
				.then((response) => resolve(response.data))
				.catch((error) => reject(error));
		}),
};
