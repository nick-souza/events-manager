import { AxiosError, AxiosRequestConfig } from "axios";
import { useState } from "react";
import { api } from "../services/api";
import { useNotification } from "./useNotification";

interface UseRequestResult {
	loading: boolean;
	request: {
		get: <T>(url: string, config?: AxiosRequestConfig) => Promise<Response<T>>;
		post: <T>(url: string, data: T, config?: AxiosRequestConfig) => Promise<Response<T>>;
		put: <T>(url: string, data: T, config?: AxiosRequestConfig) => Promise<Response<T>>;
		patch: <T>(url: string, data: T, config?: AxiosRequestConfig) => Promise<Response<T>>;
		delete: <T>(url: string, config?: AxiosRequestConfig) => Promise<Response<T>>;
	};
}

type Response<T> = {
	data: T | null;
	success: boolean;
};

type ErrorObj = {
	message: string;
};

export const useRequest = (): UseRequestResult => {
	const notify = useNotification();
	const [loading, setLoading] = useState<boolean>(false);

	const handleRequest = async <T,>(request: () => Promise<T>): Promise<Response<T>> => {
		setLoading(true);

		try {
			const result = await request();

			return { data: result, success: true } as Response<T>;
		} catch (err) {
			errorHandler(err as AxiosError<ErrorObj>);
		} finally {
			setLoading(false);
		}

		return { data: null, success: false } as Response<T>;
	};

	const errorHandler = (errorResponse: AxiosError<ErrorObj>) => {
		const message = errorResponse.response?.data?.message;

		if (message) {
			notify.error(message);
		} else {
			notify.error("An error occurred");
		}
	};

	const request = {
		get: <T,>(url: string, config?: AxiosRequestConfig) => handleRequest(() => api.get<T>(url, config)),
		post: <T,>(url: string, data: T, config?: AxiosRequestConfig) => handleRequest(() => api.post<T>(url, data, config)),
		put: <T,>(url: string, data: T, config?: AxiosRequestConfig) => handleRequest(() => api.put<T>(url, data, config)),
		patch: <T,>(url: string, data: T, config?: AxiosRequestConfig) => handleRequest(() => api.patch<T>(url, data, config)),
		delete: <T,>(url: string, config?: AxiosRequestConfig) => handleRequest(() => api.delete<T>(url, config)),
	};

	return { loading, request };
};
